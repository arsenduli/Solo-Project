package com.betaplan.soloproject.soloprojectt.controllers;

import com.betaplan.soloproject.soloprojectt.models.*;
import com.betaplan.soloproject.soloprojectt.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class MainConntrollers {
    @Autowired
    private UserServices userServices;

    @Autowired
    private ProductServices productServices;

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private OrderServices orderServices;

    @Autowired
    private ReviewsServices reviewsServices;



    // USER VIEW
    @GetMapping("/accountUser")
    public String user(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") User newLogin) {

        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "user";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
                           HttpSession session) {

        // TO-DO Later -- call a register method in the service
        userServices.register(newUser, result);
        // to do some extra validations and create a new user!

        if (result.hasErrors()) {
            // Be sure to send in the empty LoginUser before
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "user";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", newUser.getId());
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
                        HttpSession session) {

        // Add once service is implemented:
        User user = userServices.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "user";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());
        return "redirect:/";
    }


    @GetMapping("/")
    public String index(Model model, HttpSession session){
        model.addAttribute("allProduct", productServices.getNew());
        model.addAttribute("allProducts", productServices.getAllProduct());
        model.addAttribute("allCategory", categoryServices.getall());
        Long userIds = (Long) session.getAttribute("userId");
        if (userIds != null){
            model.addAttribute("user", userServices.findUserId(userIds));
            model.addAttribute("userCart", userServices.findUserId(userIds).getAddCart().size());
            model.addAttribute("userWishlist", userServices.findUserId(userIds).getAddWishlist().size());
        }

        return "index";
    }

    @GetMapping("/search/{product}")
    public String search(@PathVariable("product") String product, Model model, HttpSession session){
        model.addAttribute("productSerch" , productServices.search(product));
        model.addAttribute("howsing", product);
        Long userIds = (Long) session.getAttribute("userId");
        if (userIds != null) {
            model.addAttribute("user", userServices.findUserId(userIds));
            model.addAttribute("userCart", userServices.findUserId(userIds).getAddCart().size());
            model.addAttribute("userWishlist", userServices.findUserId(userIds).getAddWishlist().size());
        }
        return "store";
    }
    @PostMapping("/search")
    public String searchput(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("productSerch" , productServices.search(name));
        return "redirect:/search/" + name;
    }

    @GetMapping("/addCart/{id}")
    public String cart(@PathVariable("id")Long id, HttpSession session){
        Long userIds = (Long) session.getAttribute("userId");
        User user = userServices.findUserId(userIds);
        Product product = productServices.getByIdProduct(id);

        user.getAddCart().add(product);
        productServices.updateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteCart(@PathVariable("id")Long id, HttpSession session){
        Long userIds = (Long) session.getAttribute("userId");
        User user = userServices.findUserId(userIds);
        Product product = productServices.getByIdProduct(id);

        user.getAddCart().remove(product);
        productServices.updateProduct(product);
        return "redirect:/";
    }
    @GetMapping("/wishlist/{id}")
    public String wishlist(@PathVariable("id")Long id, HttpSession session){
        Long userIds = (Long) session.getAttribute("userId");
        User user = userServices.findUserId(userIds);
        Product product = productServices.getByIdProduct(id);

        user.getAddWishlist().add(product);
        productServices.updateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/delete/wishlist/{id}")
    public String deleteWishlist(@PathVariable("id")Long id, HttpSession session){
        Long userIds = (Long) session.getAttribute("userId");
        User user = userServices.findUserId(userIds);
        Product product = productServices.getByIdProduct(id);

        user.getAddWishlist().remove(product);
        productServices.updateProduct(product);
        return "redirect:/";
    }


    @GetMapping("/categories/{id}")
    public String showCategoriPro(@PathVariable("id")Long id, Model model, HttpSession session){
        Categories categoriesId = categoryServices.getById(id);
        model.addAttribute("cateName", categoriesId);
        model.addAttribute("categoriPro" , productServices.getByCategory(categoriesId));
        Long userIds = (Long) session.getAttribute("userId");
        if (userIds != null) {
            model.addAttribute("user", userServices.findUserId(userIds));
            model.addAttribute("userCart", userServices.findUserId(userIds).getAddCart().size());
            model.addAttribute("userWishlist", userServices.findUserId(userIds).getAddWishlist().size());
        }
        return "categoryProduct";
    }

    @GetMapping("/products/{id}")
    public String  showProduct(@PathVariable("id") Long id, Model model, @ModelAttribute("reviews") Reviews reviews, HttpSession session){
        Product productId = productServices.getByIdProduct(id);
        model.addAttribute("product" , productId);
        model.addAttribute("allpro" , productServices.getAllProduct());
        model.addAttribute("revi" , reviewsServices.getByProduct(productId));
        model.addAttribute("reviewTotal", productServices.getByIdProduct(id).getReviews().size());
        Long userIds = (Long) session.getAttribute("userId");
        if (userIds != null) {
            model.addAttribute("user", userServices.findUserId(userIds));
            model.addAttribute("userCart", userServices.findUserId(userIds).getAddCart().size());
            model.addAttribute("userWishlist", userServices.findUserId(userIds).getAddWishlist().size());
        }
        return "product";
    }

    @PostMapping("/makeReviews/{id}")
    public String reviews(@PathVariable("id")Long id ,@Valid @ModelAttribute("reviews") Reviews reviews,BindingResult result,  Model model, HttpSession session){
        Product productId = productServices.getByIdProduct(id);
        Long userIds = (Long) session.getAttribute("userId");
        User user = userServices.findUserId(userIds);
        if (result.hasErrors()){
            model.addAttribute("product" , productId);
            model.addAttribute("allpro" , productServices.getAllProduct());
            model.addAttribute("revi" , reviewsServices.getByProduct(productId));
            model.addAttribute("reviewTotal", productServices.getByIdProduct(id).getReviews().size());
            return "product";
        }
        reviews.setUserReview(user);
        reviews.setProductReviews(productId);
        reviewsServices.createRevi(reviews);
        return "redirect:/products/"+ id;


    }


    @GetMapping("/checkout")
    public String checkout(@ModelAttribute("order") Order order, Model model , HttpSession session){
        Long userIds = (Long) session.getAttribute("userId");
        User user = userServices.findUserId(userIds);
        List<Product> pro = user.getAddCart();
        if (userIds != null) {
            model.addAttribute("idUsr", user);
            model.addAttribute("orderUser", orderServices.getOrderByUser(user));
            model.addAttribute("userCart", userServices.findUserId(userIds).getAddCart().size());
            model.addAttribute("userWishlist", userServices.findUserId(userIds).getAddWishlist().size());
        }
        return "checkout";
    }


    // not compleate
    @PostMapping("/makeOrder")
    public String createOrder(@Valid @ModelAttribute("order") Order order,BindingResult result,  Model model, HttpSession session){
        Long userIds = (Long) session.getAttribute("userId");
        User user = userServices.findUserId(userIds);
        List<Product> pro = user.getAddCart();
        if (result.hasErrors()){
            model.addAttribute("idUsr", user);
            model.addAttribute("orderUser", orderServices.getOrderByUser(user));
            model.addAttribute("userCart", userServices.findUserId(userIds).getAddCart().size());
            model.addAttribute("userWishlist", userServices.findUserId(userIds).getAddWishlist().size());
            return "checkout";
        }
        order.setUserOrder(user);
        orderServices.createOrder(order);
        return "redirect:/";


    }
















    // ADNIN VIEW


    @GetMapping("/accountAdmin")
    public String admin(Model model, @ModelAttribute("newAdmin") Admin newAdmin,
                        @ModelAttribute("newLoginAdmin") Admin newLoginAdmin) {

        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newAdmin", new Admin());
        model.addAttribute("newLoginAdmin", new LoginAdmin());
        return "admin";
    }

    @PostMapping("/registerAdmin")
    public String registerAdmin(@Valid @ModelAttribute("newAdmin") Admin newAdmin, BindingResult result, Model model,
                           HttpSession session) {

        // TO-DO Later -- call a register method in the service
        adminServices.registerAdmin(newAdmin, result);
        // to do some extra validations and create a new user!

        if (result.hasErrors()) {
            // Be sure to send in the empty LoginUser before
            // re-rendering the page.
            model.addAttribute("newLoginAdmin", new LoginAdmin());
            return "admin";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("adminId", newAdmin.getId());
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/loginAdmin")
    public String loginAdmin(@Valid @ModelAttribute("newLoginAdmin") LoginAdmin newLoginAdmin, BindingResult result, Model model,
                        HttpSession session) {

        // Add once service is implemented:
        Admin admin = adminServices.loginAdmin(newLoginAdmin, result);

        if (result.hasErrors()) {
            model.addAttribute("newAdmin", new Admin());
            return "admin";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("adminId", admin.getId());
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model , HttpSession session){
        if (session.getAttribute("adminId") == null) {
            return "redirect:/";
        }
        Long adminIds = (Long) session.getAttribute("adminId");
        Admin admin = adminServices.findAdminId(adminIds);
        model.addAttribute("admin", adminServices.findAdminId(adminIds));
        model.addAttribute("categories", categoryServices.getall());
        model.addAttribute("proAdmn" , productServices.adminProduct(admin));
        return "adminDashboard";
    }





        private static String UPLOADED_FOLDER = "src/main/resources/static/img/";

    @GetMapping("/products/new")
    public String newProduct(@ModelAttribute("createProduct") Product createProduct,Model model , HttpSession session){
        if (session.getAttribute("adminId") == null) {
            return "redirect:/";
        }
        return "newProduct";
    }
    @PostMapping("/products/new")
    public String createProduct(@Valid @ModelAttribute("createProduct") Product createProduct,BindingResult result ,@RequestParam("pic") MultipartFile file, HttpSession session , RedirectAttributes redirectAttributes){
        Long adminIds = (Long) session.getAttribute("adminId");
        Admin adminId = adminServices.findAdminId(adminIds);
        if (result.hasErrors()){
            return "newProduct";
        }
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("massage", "Field cannot be empty!!!");
            return "newProduct";
        }

        try{
            byte[] bytes =file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            String url = "/img/" + file.getOriginalFilename();
            createProduct.setAdminProduct(adminId);
            createProduct.setUrl(url);
            productServices.createProduct(createProduct);
        } catch (IOException e){
            e.printStackTrace();
        }

        return "redirect:/admin/dashboard";
    }


    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id")Long id, @ModelAttribute("editProduct") Product editProduct,Model model , HttpSession session){
        if (session.getAttribute("adminId") == null) {
            return "redirect:/";
        }
        model.addAttribute("createProduct" , productServices.getByIdProduct(id));
        return "editProduct";
    }


    @PutMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id,@Valid @ModelAttribute("editProduct") Product editProduct,BindingResult result ,@RequestParam("pic") MultipartFile file, HttpSession session , RedirectAttributes redirectAttributes){
        Long adminIds = (Long) session.getAttribute("adminId");
        Admin adminId = adminServices.findAdminId(adminIds);
        Product product = productServices.getByIdProduct(id);
        if (result.hasErrors()){
            return "editProduct";
        }
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("massage", "Field cannot be empty!!!");
            return "editProduct";
        }

        try{
            byte[] bytes =file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            String url = "/img/" + file.getOriginalFilename();
            editProduct.setAdminProduct(adminId);
            editProduct.setUrl(url);
            productServices.createProduct(editProduct);
        } catch (IOException e){
            e.printStackTrace();
        }

        return "redirect:/admin/dashboard";
    }





    @GetMapping("/category/new")
    public String newCategory(Model model, HttpSession session){
        if (session.getAttribute("adminId") == null) {
            return "redirect:/";
        }
        return "newCategory";
    }
    @PostMapping("/category/new")
    public String addCategory(@RequestParam("pic")MultipartFile file, @RequestParam("name")String name , HttpSession session , RedirectAttributes redirectAttributes){
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("massage", "Field cannot be empty!!!");
            return "newCategory";
        }
        if (name.isEmpty()){
            redirectAttributes.addFlashAttribute("massageName", "Name cannot be empty!!!");
            return "newCategory";
        }
        try{
            byte[] bytes =file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            String url = "/img/" + file.getOriginalFilename();
            categoryServices.uploadPicCategoris(name,url);
        } catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:/admin/dashboard";
    }



    @GetMapping("/admin/categories/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model , HttpSession session){
        if (session.getAttribute("adminId") == null) {
            return "redirect:/";
        }
        Long adminIds = (Long) session.getAttribute("adminId");
        Admin adminId = adminServices.findAdminId(adminIds);
        Categories category = categoryServices.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("assingProduct", productServices.getByadminAndCate(adminId , category));
        model.addAttribute("unassingProduct", productServices.getNotContaingAdminPro(adminId , category));
        return "showCategori";
    }
    @PostMapping("/categories/{id}")
    public String addInProduct(@PathVariable("id") Long id, @RequestParam("productId") Long productId, Model model){
        Categories category = categoryServices.getById(id);
        Product product = productServices.getByIdProduct(productId);
        category.getProducts().add(product);
        categoryServices.updateCategory(category);
        return "redirect:/admin/categories/"+id;
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id){
        productServices.deleteProduct(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
