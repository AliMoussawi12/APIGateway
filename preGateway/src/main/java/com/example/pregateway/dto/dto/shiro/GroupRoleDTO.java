package com.example.pregateway.dto.dto.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * This class used for convert ApplicationGroupRole data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupRoleDTO {

    private long id;
    
    @NotNull
    @NotBlank(message = "Name role is mandatory")
    @Size(min = 1, max = 255, message = "size must be between 1 and 255")
    private String name;

    @NotNull
    @NotBlank(message = "Code role is mandatory")
    @Size(min = 1, max = 50, message = "The code role '${validatedValue}' must be between {min} and {max} characters long")
    private String code;

}