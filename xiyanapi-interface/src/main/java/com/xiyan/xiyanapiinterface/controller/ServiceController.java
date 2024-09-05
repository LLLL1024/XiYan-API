package com.xiyan.xiyanapiinterface.controller;

import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.xiyan.xiyanapiclientsdk.model.User;
import com.xiyan.xiyanapiinterface.model.ImgRes;
import com.xiyan.xiyanapiinterface.model.TalkRes;
import icu.qimuu.qiapisdk.exception.ApiException;
import icu.qimuu.qiapisdk.model.params.RandomWallpaperParams;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.xiyan.xiyanapiinterface.utils.RequestUtils.get;

/**
 * 接口服务：包括获取名称接口和第三方接口
 *
 * @author xiyan
 */
@RestController
@RequestMapping("/")
public class ServiceController {

    // TODO 接口校验请求头（是否包含 API 网关染色的请求头）

    /**
     * 2. 随机毒鸡汤
     *
     * @return
     */
    @GetMapping("/poisonousChickenSoup")
    public String getPoisonousChickenSoup() {
        return get("https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json");// 真实的第三方接口地址
    }

    /**
     * 3. 随机壁纸
     *
     * @param randomWallpaperParams
     * @return
     * @throws ApiException
     */
    @GetMapping("/randomWallpaper")
    public String randomWallpaper(RandomWallpaperParams randomWallpaperParams) throws ApiException {
//        String baseUrl = "https://api.btstu.cn/sjbz/api.php";
//        String baseUrl = "https://api.vvhan.com/api/view?type=json";
//        String url = buildUrl(baseUrl, randomWallpaperParams);
//        if (StringUtils.isAllBlank(randomWallpaperParams.getLx(), randomWallpaperParams.getMethod())) {
//            url = url + "?format=json";
//        } else {
//            url = url + "&format=json";
//        }
//        return JSONUtil.toBean(get(url), RandomWallpaperResponse.class);
        //获取随机图片
        HttpRequest request = HttpRequest.get("https://api.btstu.cn/sjbz/api.php?format=json");
        String json = request.execute().body();
        //解析JSON
        Gson gson = new Gson();
        ImgRes imgRes = gson.fromJson(json, ImgRes.class);
        return imgRes.getImgurl();
    }

    /**
     * 4. 随机土味情话
     *
     * @return
     */
    @GetMapping("/loveTalk")
    public String randomLoveTalk() {
//        return get("https://api.vvhan.com/api/love");
        //获取随机土味情话
        HttpRequest request = HttpRequest.get("https://api.uomg.com/api/rand.qinghua?format=json");
        String json = request.execute().body();
        //解析JSON
        Gson gson = new Gson();
        TalkRes talkRes = gson.fromJson(json, TalkRes.class);
        return talkRes.getContent();
    }

    /**
     * 5. 每日一句励志英语
     *
     * @return
     */
    @GetMapping("/en")
    public String dailyEnglish() {
        return get("https://api.vvhan.com/api/dailyEnglish?type=sj");
    }

    /**
     * 6. 随机歌曲
     *
     * @return
     */
    @GetMapping("/music")
    public String randomMusic() {
        return get("https://api.vvhan.com/api/wyMusic/热歌榜?type=json");
    }

    /**
     * 7. 获取输入名称
     *
     * @param user    leapi-client-sdk 包的 User 对象，只有一个 name 属性
     * @param request
     * @return
     */
    @PostMapping("/name")
    public String getNameByJSON(@RequestBody User user, HttpServletRequest request) {
        return "POST 你的名字是：" + user.getUsername();
    }
}