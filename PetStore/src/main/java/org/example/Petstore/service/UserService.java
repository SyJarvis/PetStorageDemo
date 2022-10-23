package org.example.Petstore.service;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.HexUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.example.Petstore.utils.WeBASEUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Value("${system.contract.adoptionAddress}")
    String contractAddress;
    public static final String ABI = org.example.Petstore.utils.IOUtil.readResourceAsString("abi/Adoption.abi");

    @Autowired
    WeBASEUtils weBASEUtils;
    public Dict register(String userAddress) {
        List funcParam = new ArrayList();
        funcParam.add(userAddress);
        Dict result = weBASEUtils.commonReq(userAddress, "register", funcParam, ABI, "Adoption", contractAddress);
//        System.out.println(result.toString());
        JSONObject respBody = JSONUtil.parseObj(result.get
                ("result"));
        System.out.println(respBody.toString());
        String output = (String) respBody.get("output");
//        System.out.println(output);
        System.out.println("========================");
        long resInt = HexUtil.hexToLong(output.substring(2));
        result.set("result", resInt);
        return result;
    }
    public Dict login(String userAddress) {
        List funcParam = new ArrayList();
        System.out.println("===============");
        System.out.println(userAddress);
        funcParam.add(userAddress);
        Dict result = weBASEUtils.commonReq(userAddress, "login", funcParam, ABI, "Adoption", contractAddress);
        System.out.println(result);
        System.out.println(result.get("result"));
        JSONArray respBody = JSONUtil.parseArray(result.get("result"));
        System.out.println(respBody.toString());
        if (respBody.size() > 0) {
            result.set("result", respBody.get(0));
        }
        return result;
    }
}