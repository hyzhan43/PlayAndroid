package zqx.rj.com.playandroid.other.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.account.data.bean.LoginRsp
import zqx.rj.com.playandroid.account.data.bean.RegisterRsp
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp
import zqx.rj.com.playandroid.main.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.main.home.data.bean.CommonWebRsp
import zqx.rj.com.playandroid.main.home.data.bean.HomeArticleRsp
import zqx.rj.com.playandroid.mine.collect.data.bean.CollectRsp
import zqx.rj.com.playandroid.main.navigation.data.bean.NaviCategoryRsp
import zqx.rj.com.playandroid.main.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.main.project.data.bean.ProjectTreeRsp
import zqx.rj.com.playandroid.main.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.main.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.mine.todo.data.bean.PageRsp
import zqx.rj.com.playandroid.mine.todo.data.bean.TodoRsp
import zqx.rj.com.playandroid.main.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.main.wechat.data.bean.WxArticleRsp

/**
 * author：  HyZhan
 * created： 2018/10/10 16:43
 * desc：    Api  (来自 hongyang大神的玩Android Api -> http://www.wanandroid.com/blog/show/2 )
 */
interface ApiService {

    @POST(API.LOGIN)
    fun loginAsync(@Query("username") username: String,
                   @Query("password") password: String): Deferred<BaseResponse<LoginRsp>>

    @POST(API.REGISTER)
    fun registerAsync(@Query("username") username: String,
                      @Query("password") password: String,
                      @Query("repassword") repassword: String): Deferred<BaseResponse<RegisterRsp>>

    @GET("banner/json")
    fun getBannerAsync(): Deferred<BaseResponse<List<BannerRsp>>>

    @GET("article/list/{page}/json")
    fun getHomeArticleAsync(@Path("page") page: Int): Deferred<BaseResponse<HomeArticleRsp>>

    @GET("hotkey/json")
    fun getHotKeyAsync(): Deferred<BaseResponse<List<HotKeyRsp>>>

    @POST("article/query/{page}/json")
    fun searchAsync(@Path("page") page: Int, @Query("k") key: String):
            Deferred<BaseResponse<SearchResultRsp>>

    @GET("friend/json")
    fun getCommonWebAsync(): Deferred<BaseResponse<List<CommonWebRsp>>>

    @GET("navi/json")
    fun getCategoryAsync(): Deferred<BaseResponse<List<NaviCategoryRsp>>>

    @GET("tree/json")
    fun getTreeAsync(): Deferred<BaseResponse<List<TopTreeRsp>>>

    @GET("article/list/{page}/json")
    fun getArticleTreeAsync(@Path("page") page: Int, @Query("cid") id: Int):
            Deferred<BaseResponse<TreeArticleRsp>>

    @GET("lg/collect/list/{page}/json")
    fun getCollectArticleAsync(@Path("page") page: Int): Deferred<BaseResponse<CollectRsp>>

    @POST("lg/collect/{id}/json")
    fun collectAsync(@Path("id") id: Int): Deferred<BaseResponse<EmptyRsp>>

    @POST("lg/uncollect_originId/{id}/json")
    fun unCollectAsync(@Path("id") id: Int): Deferred<BaseResponse<EmptyRsp>>

    @POST("lg/uncollect/{id}/json")
    fun unMyCollectAsync(@Path("id") id: Int, @Query("originId") originId: Int): Deferred<BaseResponse<EmptyRsp>>

    @GET("project/tree/json")
    fun getProjectTreeAsync(): Deferred<BaseResponse<List<ProjectTreeRsp>>>

    @GET("project/list/{page}/json")
    fun getProjectsAsync(@Path("page") page: Int, @Query("cid") cid: Int): Deferred<BaseResponse<ProjectRsp>>

    @GET("wxarticle/chapters/json")
    fun getWeChatNameAsync(): Deferred<BaseResponse<List<WeChatNameRsp>>>

    @GET("wxarticle/list/{id}/{page}/json")
    fun getWxArticleAsync(@Path("id") id: Int, @Path("page") page: Int)
            : Deferred<BaseResponse<WxArticleRsp>>

    @GET("lg/todo/v2/list/{page}/json")
    fun getTodoListAsync(@Path("page") page: Int,
                         @Query("status") status: Int,
                         @Query("type") type: Int,
                         @Query("priority") priority: Int,
                         @Query("orderby") orderby: Int)
            : Deferred<BaseResponse<PageRsp<TodoRsp>>>

    @POST("lg/todo/done/{id}/json")
    fun finishTodoAsync(@Path("id") id: Int,
                        @Query("status") status: Int)
            : Deferred<BaseResponse<EmptyRsp>>

    @POST("lg/todo/delete/{id}/json")
    fun deleteTodoAsync(@Path("id") id: Int): Deferred<BaseResponse<EmptyRsp>>

    @POST("lg/todo/add/json")
    fun saveTodoAsync(@Query("title") title: String,
                      @Query("date") time: String,
                      @Query("type") type: Int,
                      @Query("content") content: String): Deferred<BaseResponse<EmptyRsp>>

    @POST("lg/todo/update/{id}/json")
    fun updateTodoAsync(@Path("id") id: Int,
                        @Query("title") title: String,
                        @Query("date") time: String,
                        @Query("status") status: Int,
                        @Query("type") type: Int,
                        @Query("content") content: String,
                        @Query("priority") priority: Int
    ): Deferred<BaseResponse<EmptyRsp>>
}