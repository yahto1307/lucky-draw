package com.yahto.hydra.lucky.draw.web;

import com.yahto.hydra.lucky.draw.model.KillResult;
import com.yahto.hydra.lucky.draw.model.result.Result;
import com.yahto.hydra.lucky.draw.service.PrizeKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yahto on 2018/10/17 11:10 AM
 *
 * @author yahto
 */
@Controller
@RequestMapping("/prizeKill")
public class PrizeKillController {
    @Autowired
    private PrizeKillService prizeKillService;

    @RequestMapping("/prizeKillByPessimisticLock.ajax")
    @ResponseBody
    public Result prizeKillByPessimisticLock(@RequestParam("killItemId") Long killItemId,
                                             @RequestParam("userId") Long userId) {
        KillResult killResult = prizeKillService.prizeKill(killItemId, userId);
        return new Result().add("killResult", killResult);
    }

}
