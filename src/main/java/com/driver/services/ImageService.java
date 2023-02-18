package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);  //here we are saving in blog repo and due to cascading effect
                                     // it automatically save the image(child)
        return image;

    }

    public void deleteImage(Integer id){
         imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        String [] screenArray = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();

        String imageDimension = image.getDimensions();
        String [] imageArray = imageDimension.split("X");

        int screen_length = Integer.parseInt(screenArray[0]);
        int screen_breadth = Integer.parseInt(screenArray[1]);

        int image_length = Integer.parseInt(screenArray[0]);
        int image_breadth = Integer.parseInt(screenArray[1]);

        return  numberOfImages( screen_length,screen_breadth,image_length, image_breadth);
    }

    public int numberOfImages ( int screen_length,int screen_breadth,int image_length,int image_breadth){
        int len1 = screen_length/image_length;
        int len2 = screen_breadth/image_breadth;

        return len1*len2;
    }
}
