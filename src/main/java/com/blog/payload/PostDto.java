package com.blog.payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=2,message="Tittle shoud be atleast 2 character")//only string not integer to apply
    private String tittle;
    @NotEmpty
    @Size(min=2)//give defult message  //"must not be empty"
    //@Size(min=2,message="Description shoud be  atleast 4 character")//only string not integer to apply
    private String description;
    @NotEmpty
    @Size(min=2,message="content shoud be  atleast 4 character")//only
    private String content;
    //private String message;
}
