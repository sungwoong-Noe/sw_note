package com.note.swnote.domain;

import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.CategoryResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_seq")
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Article> articles = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;


    @OneToMany(mappedBy = "parent")
    private List<Category> childCategories = new ArrayList<>();

    public void setParentCategory(Category parent) {

        if (this.parent != null) {
            this.parent.getChildCategories().remove(this);
        }

        this.parent = parent;
        parent.getChildCategories().add(this);
    }

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


    public CategoryResponse toParentResponse() {


        CategoryResponse.CategoryResponseBuilder parentResponseBuilder = CategoryResponse.builder()
                .id(this.id)
                .categoryName(this.categoryName);

        if (this.childCategories.size() > 0) {
            return parentResponseBuilder.childList(
                    this.childCategories.stream()
                            .map(child -> ChildResponse.builder()
                                    .id(child.getId())
                                    .categoryName(child.getCategoryName())
                                    .build())
                            .collect(Collectors.toList()
                            ))
                    .build();
        } else {
            return parentResponseBuilder.build();
        }
    }


    public ChildResponse toChildResponse() {
        return ChildResponse.builder()
                .id(this.id)
                .categoryName(this.categoryName)
                .parent(this.parent.toParentResponse())
                .build();
    }
}
