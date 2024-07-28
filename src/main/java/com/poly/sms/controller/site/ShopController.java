package com.poly.sms.controller.site;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.sms.entity.Category;
import com.poly.sms.entity.Product;
import com.poly.sms.entity.ProductImage;
import com.poly.sms.entity.ProductWithImage;
import com.poly.sms.service.CategoryService;
import com.poly.sms.service.ProductImageService;
import com.poly.sms.service.ProductService;

@Controller
@RequestMapping("sms")
public class ShopController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @RequestMapping("shop")
    public String shop(Model model) {
        PageRequest pageable = PageRequest.of(0, 4);

        Page<Product> pages = productService.findAllPage(pageable);

        List<ProductWithImage> productWithImages = getProductWithImages(pages.getContent());

        List<Category> categories = categoryService.findAll();

        model.addAttribute("productWithImages", productWithImages);
        model.addAttribute("categories", categories);
        model.addAttribute("pages", pages);
        model.addAttribute("selectedTab", "all");
        return "site/shop";
    }

    @RequestMapping("shop/categories/{id}")
    public String shopWithCategory(Model model, @PathVariable("id") int id) {
        PageRequest pageable = PageRequest.of(0, 4);

        Page<Product> pages = productService.findProductsByCategoryId(id, pageable);

        List<ProductWithImage> productWithImages = getProductWithImages(pages.getContent());

        List<Category> categories = categoryService.findAll();

        model.addAttribute("productWithImages", productWithImages);
        model.addAttribute("categories", categories);
        model.addAttribute("pages", pages);
        model.addAttribute("selectedCategoryId", id);
        model.addAttribute("selectedTab", "");
        return "site/shop";
    }

    @RequestMapping("shop/pages")
    public String shopWithPage(Model model, @RequestParam("p") Optional<Integer> p) {
        PageRequest pageable = PageRequest.of(p.orElse(0), 4);
        Page<Product> pages = productService.findAllPage(pageable);

        List<ProductWithImage> productWithImages = getProductWithImages(pages.getContent());

        List<Category> categories = categoryService.findAll();

        model.addAttribute("productWithImages", productWithImages);
        model.addAttribute("categories", categories);
        model.addAttribute("pages", pages);
        model.addAttribute("selectedTab", "all");
        return "site/shop";
    }

    @RequestMapping("shop/keywords")
    public String shopWithKeywords(Model model, @RequestParam("productName") String productName) {
        PageRequest pageable = PageRequest.of(0, 4);
        Page<Product> pages = productService.findByProductNameContaining(productName, pageable);

        List<ProductWithImage> productWithImages = getProductWithImages(pages.getContent());

        List<Category> categories = categoryService.findAll();

        model.addAttribute("productWithImages", productWithImages);
        model.addAttribute("categories", categories);
        model.addAttribute("pages", pages);
        model.addAttribute("selectedTab", "all");
        return "site/shop";
    }

    private List<ProductWithImage> getProductWithImages(List<Product> products) {

        List<ProductImage> productImages = productImageService.findAll()
                .stream()
                .filter(productImage -> products.contains(productImage.getProduct()))
                .collect(Collectors.toList());

        Map<Product, ProductImage> productImageMap = productImages.stream()
                .collect(Collectors.toMap(
                        ProductImage::getProduct,
                        pi -> pi,
                        (existing, replacement) -> existing));

        List<ProductWithImage> productWithImages = products.stream()
                .map(product -> new ProductWithImage(product, productImageMap.get(product)))
                .collect(Collectors.toList());

        return productWithImages;
    }
}
