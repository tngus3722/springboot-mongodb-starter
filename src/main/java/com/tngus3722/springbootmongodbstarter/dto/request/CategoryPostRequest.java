package com.tngus3722.springbootmongodbstarter.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryPostRequest {
    @NotNull
    private String id;
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
}
