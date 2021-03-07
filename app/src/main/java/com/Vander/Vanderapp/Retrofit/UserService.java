package com.Vander.Vanderapp.Retrofit;

import com.Vander.Vanderapp.ModelResponse.CategoryListModel;
import com.Vander.Vanderapp.model.Secondcategory_itemlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    //getcategories
    @GET("getcategories")
    Call<List<CategoryListModel>> getcategories();

    //getsubcategory
    @GET("getsubcategory/{cat_id}")
    Call<List<Secondcategory_itemlist>> getSubCategories(
            @Path("cat_id") String cat_id
    );


}