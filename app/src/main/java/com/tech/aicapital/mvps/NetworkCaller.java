package com.tech.aicapital.mvps;




import com.tech.aicapital.bots.datalist.BotDataResponse;
import com.tech.aicapital.cart.datalist.CarDataListResponse;
import com.tech.aicapital.cart.datalist.PinListResponse;
import com.tech.aicapital.datalist.AllBrandResponse;
import com.tech.aicapital.datalist.ArticleDataResponse;
import com.tech.aicapital.datalist.BannerDataResponse;
import com.tech.aicapital.datalist.CategoryDataResponse;
import com.tech.aicapital.datalist.CheckResponse;
import com.tech.aicapital.datalist.EarningHistoryResponse;
import com.tech.aicapital.datalist.GeneologydataResponse;
import com.tech.aicapital.datalist.IfscResponse;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.tech.aicapital.datalist.MemberDetailResponse;
import com.tech.aicapital.datalist.MenuResponse;
import com.tech.aicapital.datalist.MyTradeResponse;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.datalist.OperatorResponse;
import com.tech.aicapital.datalist.PlanListResponse;
import com.tech.aicapital.datalist.ProductResponse;
import com.tech.aicapital.datalist.ScratchListResponse;
import com.tech.aicapital.datalist.SubcatDataResponse;
import com.tech.aicapital.datalist.TransTypeResponse;
import com.tech.aicapital.datalist.TransactionHistoryResponse;
import com.tech.aicapital.datalist.VideoDataListResponse;
import com.tech.aicapital.datalist.VillageDataResponse;
import com.tech.aicapital.followups.datalist.AccountDataResponse;
import com.tech.aicapital.followups.datalist.CustomerDataResponse;
import com.tech.aicapital.followups.datalist.EducationDataResponse;
import com.tech.aicapital.followups.datalist.FollowupDataList;
import com.tech.aicapital.followups.datalist.FollowupDataListResponse;
import com.tech.aicapital.datalist.SimpleResponse;
import com.tech.aicapital.followups.datalist.TradingResponse;
import com.tech.aicapital.followups.datalist.UserDashboardDataResponse;

import java.util.zip.Checksum;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * Created by Hadi on 3/14/2018.
 */

public interface NetworkCaller {

    @FormUrlEncoded
    @POST("getMygeneologylevelwise")
    Call<GeneologydataResponse> getMygeneology(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("get_all_transaction")
    Call<TransactionHistoryResponse> getMyTransactionHistory(@Field("user_id") String member_id, @Field("type_id") String type_id);


    @FormUrlEncoded
    @POST("getUserDetailbyMobile")
    Call<MemberDataResponse> getUserDetailbyMobile(@Field("user_mobile") String user_mobile);



    @POST("getCurrentAccDetails")
    Call<AccountDataResponse> getCurrentAccDetails();

    @Multipart
    @POST("depositPayment")
    Call<NewResponse> depositPayment(@Part("trans_no") RequestBody trans_no,
                                     @Part("amount") RequestBody amount,
                                     @Part("user_id") RequestBody user_id,
                                     @Part("plan_id") RequestBody plan_id,
                                     @Part MultipartBody.Part screenshot);

    @GET
    Call<IfscResponse> getIfscode(@Url String url);

  @GET
  Call<TradingResponse> getTradingList(@Url String url);

    @FormUrlEncoded
    @POST("login")
    Call<MemberDetailResponse> getnLogin(@Field("user_id") String id,
                                         @Field("user_password") String pwd);

    @FormUrlEncoded
    @POST("getBmartUsers")
    Call<MemberDataResponse> member_details(@Field("user_id") String record_no);

    @FormUrlEncoded
    @POST("app-post.php")
    Call<SimpleResponse> userRegistration(@Field("form_type") String form_type,
                                      @Field("user_name") String user_name,
                                      @Field("user_mobile") String user_mobile,
                                      @Field("user_pass") String user_pass,
                                      @Field("work_type_id") String user_type_id,
                                     @Field("user_email") String user_email);

 @FormUrlEncoded
 @POST("get_pin_by_assign_to")
 Call<PinListResponse> getMyPinList(@Field("assigned_to") String assigned_to_member_id);
 @FormUrlEncoded
 @POST("getUserDashboardCounts")
 Call<UserDashboardDataResponse> getUserDashboardCounts(@Field("user_id") String user_id);

 @FormUrlEncoded
 @POST("getAllFollowups")
 Call<FollowupDataListResponse> getAllFollowups(@Field("customer_id") String customer_id);

 @FormUrlEncoded
 @POST("insertCustomer")
 Call<NewResponse> insertCustomer(@Field("cname") String cname,
                                     @Field("mobile_no") String mobile_no,
                                     @Field("work") String work,
                                     @Field("reference_thr") String reference_thr,
                                     @Field("m_income") String m_income,
                                     @Field("education_id") String education_id,
                                     @Field("sponser_id") String sponser_id,
                                     @Field("distict_id") String distict_id,
                                     @Field("locality_id") String locality_id);
 @FormUrlEncoded
 @POST("insertFollowup")
 Call<NewResponse> insertFollowup(@Field("cust_id") String cust_id,
                                     @Field("follow_up") String follow_up,
                                     @Field("remark") String remark,
                                     @Field("activity") String activity,
                                     @Field("follow_status") String follow_status,
                                     @Field("remind_date") String remind_date);

 @FormUrlEncoded
 @POST("getMyCustomers")
 Call<CustomerDataResponse> getMyCustomers(@Field("sponserId") String assigned_to_member_id);
 @FormUrlEncoded
 @POST("getMyTrades")
 Call<MyTradeResponse> getMyTrades(@Field("user_id") String assigned_to_member_id);

 @FormUrlEncoded
 @POST("updateProfileData")
 Call<NewResponse> updateProfileData(@Field("user_id") String user_id,
                                     @Field("aadhar") String aadhar,
                                     @Field("pan") String pan,
                                     @Field("user_email") String user_email,
                                     @Field("dob") String dob,
                                     @Field("user_name") String user_name,
                                     @Field("user_mobile") String user_mobile);


 @Multipart
 @POST("updateProfileImage")
 Call<NewResponse> updateProfileImage(@Part MultipartBody.Part img_aadharfront,
                                      @Part("user_id") RequestBody user);

    @POST("getDistrict")
    Call<VillageDataResponse> getDistrict();

    @POST("getEducationList")
    Call<EducationDataResponse> getEducationList();

    @POST("getReasons")
    Call<EducationDataResponse> getReasons();

    @FormUrlEncoded
    @POST("getTehasil")
    Call<VillageDataResponse> getTehasil(@Field("dist_code") String distcode);
    @FormUrlEncoded
    @POST("getVillages")
    Call<VillageDataResponse> getVillages(@Field("sub_code") String sub_code);


    @FormUrlEncoded
    @POST("getPlanList")
    Call<PlanListResponse> getPlanList(@Field("status") String status);
    @FormUrlEncoded
    @POST("getYouTubeList")
    Call<VideoDataListResponse> getYouTubeList(@Field("status") String status);


    @POST("getYouTubeList2")
    Call<VideoDataListResponse> getYouTubeList2();

//    @FormUrlEncoded
//    @POST("getMygeneologylevelwise")
//    Call<GeneologydataResponse> getMygeneology(@Field("user_id") String user_id);

//    @FormUrlEncoded
//    @POST("getMygeneologyFour")
//    Call<GeneologydataResponse> getMygeneologyFour(@Field("user_id") String user_id);

    @POST("getMenuList")
    Call<MenuResponse> getMenuList();

//    @POST("getAllProductList")
//    Call<AllProductListResponse> getAllProductList();

    @POST("getBrandList")
    Call<AllBrandResponse> getBrandList();

    @FormUrlEncoded
    @POST("getEarningHistory")
    Call<EarningHistoryResponse> getEarningHistory(@Field("user_id") String UserId);
    @FormUrlEncoded
    @POST("getBotList")
    Call<BotDataResponse> getBotList(@Field("status") String UserId);


    @FormUrlEncoded
    @POST("getSubcategory")
    Call<SubcatDataResponse> getSubcategory(@Field("category_id") String typeId);

    @FormUrlEncoded
    @POST("Product/AddProductToShop")
    Call<SimpleResponse> AddProductToShop(@Field("shop_id") int shop_id,@Field("product_id") int product_id,
                                                         @Field("product_unit_value") int product_unit_value,@Field("product_unit") String product_unit,
                                                         @Field("product_mrp") int product_mrp,@Field("product_discount") int product_discount,
                                                         @Field("stock") int stock,@Field("status") int status);
    @FormUrlEncoded
    @POST("member_registration")
    Call<NewResponse> makeRegistration(@Field("member_name") String member_name,
                                       @Field("member_mobile") String member_mobile,
                                       @Field("member_email") String member_email,
                                       @Field("member_dob") String member_dob,
                                       @Field("member_gender") String member_gender,
                                       @Field("member_password") String member_password,
                                       @Field("member_join_date") String member_join_date,
                                       @Field("status") String status,
                                       @Field("refer_member_id") String refer_member_id);

    @FormUrlEncoded
    @POST("TopUpfromBalance")
    Call<NewResponse> TopUpfromBalance(@Field("user_id") String user_id,
                                       @Field("amount") String amount);
    @FormUrlEncoded
    @POST("transfer_pin_to_user")
    Call<NewResponse> transfer_pin_to_user(@Field("user_id") String user_id, @Field("reference_id") String reference_id,
                                           @Field("rec_no") String rec_no, @Field("member_mobile") String member_mobile);
    @FormUrlEncoded
    @POST("activate_pin")
    Call<NewResponse> activate_pin(@Field("rec_no") String rec_no, @Field("reference_id") String reference_id,
                                   @Field("user_id") String user_id, @Field("mobile_no") String mobile_no,
                                   @Field("status") String status);
    @FormUrlEncoded
    @POST("activate_new_pin")
    Call<NewResponse> new_activate_pin(@Field("reference_id") String reference_id,
                                       @Field("user_id") String user_id,
                                       @Field("pin_no") String pin_no, @Field("mobile_no") String mobile_no);
    @FormUrlEncoded
    @POST("Bank_Withdrwal_Request")
    Call<NewResponse> Bank_Withdrwal_Request(@Field("member_id") String member_id, @Field("withdrawal_amt") String withdrawal_amt,
                                             @Field("bank_name") String bank_name, @Field("bank_ifsc") String bank_ifsc,
                                             @Field("bank_acc_no") String bank_acc_no,
                                             @Field("real_withdraw") String real_withdraw,
                                             @Field("deduction") String deduction,
                                             @Field("reason") String reason,
                                             @Field("member_mobile") String member_mobile);
    @FormUrlEncoded
    @POST("Bank_Get")
    Call<NewResponse> sendBankDetails(@Field("member_id") String member_id, @Field("amount_paid") String amount_paid,
                                      @Field("bank_name") String bank_name, @Field("bank_ifsc") String bank_ifsc,
                                      @Field("bank_account") String bank_account);
    @FormUrlEncoded
    @POST("login")
    Call<NewResponse> getLogin(@Field("member_id") String member_id,
                               @Field("member_password") String member_password);






//***************************************************************************************************************************



  @POST("getBannerList")
  Call<BannerDataResponse> getBannerList();

  @POST("getBannerList2")
  Call<BannerDataResponse> getBannerList2();



 @FormUrlEncoded
 @POST("addStock")
 Call<NewResponse> addStock(@Field("user_id") String user_id,
                            @Field("product_id") String user_name,
                            @Field("product_qty") String product_qty);

 @FormUrlEncoded
 @POST("updateMyAddress")
 Call<NewResponse> updateProfileAddess(@Field("user_id") String user_id,
                                       @Field("flat_no") String flat_no,
                                       @Field("building_name") String building_name,
                                       @Field("address_one") String address_one,
                                       @Field("address_two") String address_two,
                                       @Field("pin_code") String pin_code,
                                       @Field("lat") Double lat,
                                       @Field("longi") Double longi);
// @FormUrlEncoded
// @POST("getMyAddressDetails")
// Call<AdressResponse> getMyAddressDetails(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("newSubscribe")
    Call<NewResponse> newSubscribe(@Field("user_id") String user_id,
                                 @Field("amount") String amount,
                                 @Field("plan_id") String plan_id);


 @FormUrlEncoded
 @POST("member_registration")
 Call<NewResponse> makeRegistration(@Field("user_name") String member_name,
                                    @Field("user_mobile") String member_mobile,
                                    @Field("user_password") String member_password,
                                    @Field("refer_user_id") String refer_member_id,
                                    @Field("user_email") String user_mail,
                                    @Field("isFranch") String isFranch);


 @FormUrlEncoded
 @POST("getMyCartList")
 Call<CarDataListResponse> getMyCartList(@Field("user_id") String user_id);



 @FormUrlEncoded
 @POST("updateOrderStatus")
 Call<NewResponse> updateOrderStatus(@Field("order_id") String order_id,@Field("status") String status);

 @FormUrlEncoded
 @POST("memberBankUpdate")
 Call<NewResponse> memberBankUpdate(@Field("user_id") String user_id,
                                    @Field("bank_ifsc") String bank_ifsc,
                                    @Field("bank_acc_no") String bank_acc_no);

 @FormUrlEncoded
 @POST("newBillionireTopupID")
 Call<NewResponse> newBillionireTopupID(@Field("user_id") String user_id,@Field("refer_id") String refer_id,
                                        @Field("amount") String amount,@Field("pin_no") String pin_no,
                                        @Field("product_id") String product_id,@Field("type") String type);

 @FormUrlEncoded
 @POST("addGroupMember")
 Call<NewResponse> addGroupMember(@Field("user_id") String user_id,@Field("group_id") String group_id);


 @FormUrlEncoded
 @POST("addNewGroup")
 Call<NewResponse> addNewGroup(@Field("user_id") String user_id,
                               @Field("group_name") String group_name,
                               @Field("secretary_id") String secretary_id,
                               @Field("village_id") String village_id,
                               @Field("group_roi") String group_roi,
                               @Field("meeting_date") String meeting_date,
                               @Field("fine_amt") String fine_amt,
                               @Field("admin_id") String admin_id);

 @FormUrlEncoded
 @POST("sendOrder")
 Call<NewResponse> sendOrder(@Field("order") String record_no);


 @FormUrlEncoded
 @POST("sendCtList")
 Call<NewResponse> sendCtList(@Field("order") String order);

 @FormUrlEncoded
 @POST("generateHash")
 Call<NewResponse> generateHash(@Field("key") String key,
                                @Field("amount") String amount,
                                @Field("productName") String productName,
                                @Field("email") String email,
                                @Field("firstName") String firstname,
                                @Field("txnId") String txnId,
                                @Field("utf1") String utf1,
                                @Field("utf2") String utf2,
                                @Field("utf3") String utf3,
                                @Field("utf4") String utf4,
                                @Field("utf5") String utf5,
                                @Field("utf6") String utf6,
                                @Field("utf7") String utf7,
                                @Field("utf8") String utf8,
                                @Field("utf9") String utf9,
                                @Field("utf10") String utf10);


 @FormUrlEncoded
 @POST("update_withdrawstatus")
 Call<NewResponse> update_withdrawstatus(@Field("rec_no") String record_no,
                                         @Field("operate_by") String operate_by);

 @FormUrlEncoded
 @POST("insertProduct")
 Call<NewResponse> insertProduct(@Field("category_id") String category_id,
                                 @Field("product_name") String product_name,
                                 @Field("description") String description,
                                 @Field("discount") String discount,
                                 @Field("product_mrp") String product_mrp,
                                 @Field("unit") String unit,
                                 @Field("per_unit") String per_unit);

 @FormUrlEncoded
 @POST("insertQuote")
 Call<NewResponse> insertQuote(@Field("user_id") String user_id,
                               @Field("product_id") String product_id,
                               @Field("product_qty") String product_qty,
                               @Field("unit") String unit);

 @FormUrlEncoded
 @POST("updateQuote")
 Call<NewResponse> updateQuote(@Field("rec_no") String rec_no,
                               @Field("upby") String upby,
                               @Field("status") String status,
                               @Field("requested_cost") String requested_cost,
                               @Field("estimated_cost") String estimated_cost);
 @FormUrlEncoded
 @POST("insertVendor")
 Call<NewResponse> insertVendor(@Field("vendor_name") String vendor_name,
                                @Field("vendor_mobile") String vendor_mobile);

 @FormUrlEncoded
 @POST("insertPurchase")
 Call<NewResponse> insertPurchase(@Field("user_id") String user_id,
                                  @Field("product_id") String product_id,
                                  @Field("vendor_id") String vendor_id,
                                  @Field("unitt") String unit,
                                  @Field("unit_value") String unit_value,
                                  @Field("price_per_unit") String price_per_unit,
                                  @Field("qty") String qty,
                                  @Field("total_cost") String total_cost);
 @FormUrlEncoded
 @POST("insertSell")
 Call<NewResponse> insertSell(@Field("user_id") String user_id,
                              @Field("product_id") String product_id,
                              @Field("vendor_id") String vendor_id,
                              @Field("unitt") String unit,
                              @Field("unit_value") String unit_value,
                              @Field("price_per_unit") String price_per_unit,
                              @Field("qty") String qty,
                              @Field("total_cost") String total_cost);

 @Multipart
 @POST("insert_Product")
 Call<NewResponse> insert_Product(@Part("category_id") RequestBody category_id,
                                  @Part("product_name") RequestBody product_name,
                                  @Part("description") RequestBody description,
                                  @Part("discount") RequestBody discount,
                                  @Part("product_mrp") RequestBody product_mrp,
                                  @Part("unit") RequestBody unit,
                                  @Part("per_unit") RequestBody per_unit,
                                  @Part MultipartBody.Part ads_image,
                                  @Part("shop_id") RequestBody shopi);


 @FormUrlEncoded
 @POST("updateProduct")
 Call<NewResponse> updateProduct(@Field("product_id") String category_id,
                                 @Field("product_name") String product_name,
                                 @Field("description") String description,
                                 @Field("discount") String discount,
                                 @Field("product_mrp") String product_mrp,
                                 @Field("unit") String unit,
                                 @Field("per_unit") String per_unit,
                                 @Field("status") String status);


 @Multipart
 @POST("insertEnquiry")
 Call<NewResponse> insertEnquiry(@Part("owner_name") RequestBody owner_name,
                                 @Part("owner_village") RequestBody owner_village,
                                 @Part("owner_district") RequestBody owner_district,
                                 @Part("owner_tehasil") RequestBody owner_tehasil,
                                 @Part("owner_mobile") RequestBody owner_mobile,
                                 @Part("owner_email") RequestBody owner_email,
                                 @Part("is_100farmer") RequestBody is_100farmer,
                                 @Part("is_2guntha") RequestBody is_2guntha,
                                 @Part("is_transport") RequestBody is_transport,
                                 @Part MultipartBody.Part img_aadhar,
                                 @Part MultipartBody.Part img_pan);


 @Multipart
 @POST("insertShop")
 Call<NewResponse> insertShop(@Part("shop_name") RequestBody shop_name,
                              @Part MultipartBody.Part img_aadhar,
                              @Part("shop_contact") RequestBody shop_contact,
                              @Part("latitude") RequestBody latitude,
                              @Part("longitude") RequestBody longitude,
                              @Part("shop_address") RequestBody shop_address,
                              @Part("subcity_id") RequestBody sub_city,
                              @Part("city") RequestBody city,
                              @Part("type") RequestBody type,
                              @Part("open_time") RequestBody open_time,
                              @Part("close_time") RequestBody close_time,
                              @Part("pin_code") RequestBody pin_code,
                              @Part("user_id") RequestBody user,
                              @Part("created_by") RequestBody created_by);


 @FormUrlEncoded
 @POST("updateProductStatusofshop")
 Call<NewResponse> updateProductStatusofshop(@Field("rec_no") String rec_no,
                                             @Field("discount") String discount,
                                             @Field("product_mrp") String product_mrp,
                                             @Field("unit") String unit,
                                             @Field("per_unit") String per_unit,
                                             @Field("status") String status);

 @FormUrlEncoded
 @POST("insertShopProduct")
 Call<NewResponse> insertShopProduct(@Field("product_id") String rec_no,
                                     @Field("discount") String discount,
                                     @Field("product_mrp") String product_mrp,
                                     @Field("unit") String unit,
                                     @Field("per_unit") String per_unit,
                                     @Field("status") String status,
                                     @Field("shop_id") String shop_id);
 @FormUrlEncoded
 @POST("newTopupID")
 Call<NewResponse> newTopupID(@Field("user_id") String user_id,
                              @Field("amount") String amount,
                              @Field("pin_no") String pin_no,
                              @Field("type") String type,
                              @Field("product_id") String product_id);
 /////////////////////////// product_id = IF product_id = 0 its a group else individuals investment



 @FormUrlEncoded
 @POST("newPurchase")
 Call<NewResponse> newPurchase(@Field("user_id") String user_id,
                               @Field("amount") String amount,
                               @Field("billno") String billno,
                               @Field("operate_by") String operate_by);
 @FormUrlEncoded
 @POST("withdrawMoney")
 Call<NewResponse> withdrawMoney(@Field("user_id") String user_id,
                                 @Field("amount") String amount);
 @FormUrlEncoded
 @POST("change_password")
 Call<NewResponse> change_password(@Field("user_id") String user_id,
                                   @Field("old_password") String old_password,
                                   @Field("new_password") String new_password);


 @POST("getAllCategory")
 Call<CategoryDataResponse> getCategorylist();

 @POST("getAllTypes")
 Call<CategoryDataResponse> getAllTypes();

 @FormUrlEncoded
 @POST("getAllCategoryofShop")
 Call<CategoryDataResponse> getAllCategoryofShop(@Field("shop_id") String shop_id);

 @POST("getArticleTypes")
 Call<CategoryDataResponse> getArticleTypes();

 @FormUrlEncoded
 @POST("getCategoriesofTypes")
 Call<CategoryDataResponse> getCategoriesofTypes(@Field("type_id") String shop_id);


 @POST("getOfferCategory")
 Call<CategoryDataResponse> getOfferCategory();

 @POST("getTransTypes")
 Call<TransTypeResponse> getTransTypes();


 @FormUrlEncoded
 @POST("getProductsOfShopCategorywise")
 Call<ProductResponse> getProductsOfShopCategorywise(@Field("shop_id") String shop_id,@Field("category_id") String category_id);

 @FormUrlEncoded
 @POST("getArticles")
 Call<ArticleDataResponse> getArticles(@Field("type_id") String page_no);

 @FormUrlEncoded
 @POST("getWithdrawAmount")
 Call<NewResponse> getWithdrawAmount(@Field("user_id") String user_id);



// //
 @FormUrlEncoded
 @POST("getMyScratchcard")
 Call<ScratchListResponse> getMyScratchcard(@Field("user_id") String user_id);


 //USED
 @FormUrlEncoded
 @POST("getProductListbyCategory")
 Call<ProductResponse> getProductListbyCategory(@Field("category_id") String category_id);

 @FormUrlEncoded
 @POST("getProductListbyShopCategory")
 Call<ProductResponse> getProductListbyShopCategory(@Field("shop_id") String shop_id,@Field("category_id") String category_id);

 @FormUrlEncoded
 @POST("getAllProductListByCategory")
 Call<ProductResponse> getAllProductListByCategory(@Field("category_id") String category_id);

 @FormUrlEncoded
 @POST("getProductListbyCategory")
 Call<ProductResponse> getBaseProductListbyShopCategory(@Field("category_id") String category_id,@Field("shop_id") String shop_id);

 @FormUrlEncoded
 @POST("getProductListbyBrand")
 Call<ProductResponse> getProductListbyBrand(@Field("brand_id") String brand_id);

//    @FormUrlEncoded
//    @POST("get_member_detail")
//    Call<MemberDetailResponse> getMemberDetail(@Field("member_id") String member_id);

 @FormUrlEncoded
 @POST("get_product_list.php")
 Call<ProductResponse> getProductList(@Field("category_id") String category_id);
 //
 @FormUrlEncoded
 @POST("newPurchase")
 Call<NewResponse> getPurchase(@Field("user_id") String member_id,
                               @Field("amount") String amount,
                               @Field("billno") String billno,
                               @Field("operate_by") String operate_by,
                               @Field("coupon_id") String coupon_id,
                               @Field("coupon_amt") String coupon_amt,
                               @Field("pin_no") String pin_no,
                               @Field("product_id") String product_id);

 @FormUrlEncoded
 @POST("addItemtoCart")
 Call<NewResponse> addItemtoCart(@Field("user_id") String member_id,
                               @Field("action") String action,
                               @Field("product_id") String product_id,
                               @Field("product_unit") String product_unit,
                               @Field("product_unit_value") String product_unit_value,
                               @Field("product_mrp") String product_mrp,
                               @Field("product_qty") String product_qty,
                               @Field("discount") String discount);


 @FormUrlEncoded
 @POST("updateScratchCard")
 Call<NewResponse> updateScratchCard(@Field("user_id") String user_id,
                                     @Field("scratch_id") String scratch_id,
                                     @Field("amount") String amount,
                                     @Field("unit") String unit);

 @FormUrlEncoded
 @POST("friendTransferMoney")
 Call<NewResponse> friendTransferMoney(@Field("user_id") String user_id,
                                       @Field("friend_user_id") String friend_user_id,
                                       @Field("amount") String amount);

 @FormUrlEncoded
 @POST("pinRequestByFranch")
 Call<NewResponse> pinRequestByFranch(@Field("user_id") String user_id,
                                      @Field("franch_id") String franch_id,
                                      @Field("plan_id") String plan_id,
                                      @Field("qty") String qty,
                                      @Field("price") String price);

 @POST("get_versionno")
 Call<CheckResponse> getVersionNo();




 @FormUrlEncoded
 @POST("member_registration")
 Call<NewResponse> memberRegistration(@Field("user_name") String member_name,
                                       @Field("user_mobile") String member_mobile,
                                       @Field("user_password") String member_password,
                                       @Field("refer_user_id") String refer_member_id,
                                       @Field("user_email") String member_mail);

 @FormUrlEncoded
 @POST("insertMemberLoan")
 Call<NewResponse> insertMemberLoan(@Field("user_id") String user_id,
                                       @Field("group_id") String group_id,
                                       @Field("new_loan") String new_loan,
                                       @Field("pre_loan") String pre_loan,
                                       @Field("rec_no") String rec_no);

 @FormUrlEncoded
 @POST("takeBankLoan")
 Call<NewResponse> takeBankLoan(@Field("user_id") String user_id,
                                       @Field("group_id") String group_id,
                                       @Field("type_id") String type_id,
                                       @Field("amount") String amount);
 @FormUrlEncoded
 @POST("repayBankLoan")
 Call<NewResponse> repayBankLoan(@Field("user_id") String user_id,
                                       @Field("group_id") String group_id,
                                       @Field("type_id") String type_id,
                                       @Field("amount") String amount);

 @FormUrlEncoded
 @POST("addBankSaving")
 Call<NewResponse> addBankSaving(@Field("user_id") String user_id,
                                       @Field("group_id") String group_id,
                                       @Field("type_id") String type_id,
                                       @Field("amount") String amount);

 @FormUrlEncoded
 @POST("addBankWithdraw")
 Call<NewResponse> addBankWithdraw(@Field("user_id") String user_id,
                                   @Field("group_id") String group_id,
                                   @Field("type_id") String type_id,
                                   @Field("amount") String amount);

@FormUrlEncoded
@POST("insertPassbook")
Call<NewResponse> insertPassbook(@Field("user_id") String user_id,
                                       @Field("group_id") String group_id,
                                       @Field("new_loan") String new_loan,
                                       @Field("avail_loan") String avail_loan,
                                       @Field("saving") String saving,
                                       @Field("intrest") String intrest,
                                       @Field("fine") String fine,
                                       @Field("repay") String repay,
                                       @Field("note") String note);

 @FormUrlEncoded
 @POST("memberRegistrationGroup")
 Call<NewResponse> memberRegistrationGroup(@Field("user_name") String member_name,
                                       @Field("user_mobile") String member_mobile,
                                       @Field("user_password") String member_password,
                                       @Field("refer_user_id") String refer_member_id,
                                       @Field("user_email") String member_mail,
                                       @Field("group_id") String groupId);
 @FormUrlEncoded
 @POST("sendOtp")
 Call<NewResponse> sendOtp(@Field("mobile_no") String mobile_no);


 @FormUrlEncoded
 @POST("verifyOtp")
 Call<NewResponse> verifyOtp(@Field("mobile_no") String mobile_no,
                             @Field("otp") String otp);


}
