package com.tt.es.controller;


import com.alibaba.fastjson.JSONObject;
import com.tt.es.document.EsDemoDocument;
import com.tt.es.message.ResponseMsg;
import com.tt.es.service.IEsDemoRepositoryService;
import com.tt.es.service.IEsDemoTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("es")
public class EsDemoController {

    private Logger log = LoggerFactory.getLogger(EsDemoController.class);

    @Autowired
    private IEsDemoRepositoryService iEsDemoRepositoryService;

    @Autowired
    private IEsDemoTemplateService iEsDemoTemplateService;

    @RequestMapping("/repository/getName")
    public ResponseMsg<String> repositoryGetName(@RequestParam("name") String name) {
        ResponseMsg<String> responseMsg = new ResponseMsg<String>();
        responseMsg.setData(JSONObject.toJSONString(iEsDemoRepositoryService.findByName(name)));
        return responseMsg;
    }

    @RequestMapping("/repository/save")
    public ResponseMsg<String> repositorySave(@RequestBody EsDemoDocument document) {
        ResponseMsg<String> responseMsg = new ResponseMsg<String>();

        iEsDemoRepositoryService.save(document);
        return responseMsg;
    }

    @RequestMapping("/repository/del")
    public ResponseMsg<String> repositoryDel(@RequestParam("id") String id) {
        ResponseMsg<String> responseMsg = new ResponseMsg<String>();
        iEsDemoRepositoryService.del(id);
        return responseMsg;
    }

    @RequestMapping("/repository/update")
    public ResponseMsg<String> repositoryUpdate(@RequestBody EsDemoDocument document) {
        ResponseMsg<String> responseMsg = new ResponseMsg<String>();
        iEsDemoRepositoryService.save(document);
        return responseMsg;
    }

    @RequestMapping("/template/save")
    public ResponseMsg<String> templateSave(@RequestBody EsDemoDocument esDemoDocument) {
        ResponseMsg<String> responseMsg = new ResponseMsg<>();
        iEsDemoTemplateService.save(esDemoDocument);
        return responseMsg;
    }

    @RequestMapping("/template/getName")
    public ResponseMsg<String> templateGetName(@RequestParam("name") String name) {
        ResponseMsg<String> responseMsg = new ResponseMsg<>();
        responseMsg.setData(JSONObject.toJSONString(iEsDemoTemplateService.findByName(name)));
        return responseMsg;
    }

    @RequestMapping("/template/bulkIndex")
    public ResponseMsg<String> bulkIndex() {
        ResponseMsg<String> responseMsg = new ResponseMsg<>();
        iEsDemoTemplateService.bulkIndex();
        return responseMsg;
    }

    @RequestMapping("/template/list")
    public ResponseMsg<JSONObject> list(@RequestParam("name") String name) {
        ResponseMsg<JSONObject> responseMsg = new ResponseMsg<>();
        JSONObject list = iEsDemoTemplateService.list(name);
        responseMsg.setData(list);
        return responseMsg;
    }
}
