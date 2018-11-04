package com.yahto.hydra.lucky.draw.web;

import com.yahto.hydra.lucky.draw.model.DrawResult;
import com.yahto.hydra.lucky.draw.model.result.Result;
import com.yahto.hydra.lucky.draw.service.LuckyDrawService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by yahto on 2018/9/27 11:48 PM
 *
 * @author yahto
 */
@Controller
@RequestMapping("/luckDraw")
public class LuckDrawController {
    @Resource(name = "luckyDrawServiceImpl")
    private LuckyDrawService luckyDrawService;

    @Resource(name = "luckDrawRedisServiceImpl")
    private LuckyDrawService luckDrawRedisService;

    @RequestMapping(value = "/luckDrawByDB.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Result luckDrawByDB(@RequestParam("activityId") Long activityId,
                               @RequestParam("userId") Long userId) {
        DrawResult drawResult = luckyDrawService.luckyDraw(activityId, userId);
        return new Result().success().add("drawResult", drawResult);
    }

    @RequestMapping(value = "/luckDrawByRedis.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Result luckDrawByRedis(@RequestParam("activityId") Long activityId,
                                  @RequestParam("userId") Long userId) {
        DrawResult drawResult = luckDrawRedisService.luckyDraw(activityId, userId);
        return new Result().success().add("drawResult", drawResult);
    }
}
