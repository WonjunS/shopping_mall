package com.example.shopping_mall.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verifyIamport")
public class VerifyController {

    private final IamportClient iamportClient;
    private final String REST_API = "2600328773634706";
    private final String REST_API_SECRET = "XgywrlDXHt3KLbPRKnwi2GtCc6XPh7bR93NfYdIdELYErDIYezTzGyPj8HsOo1C3Y0e6zcIgR3OvddJX";

    public VerifyController() {
        this.iamportClient = new IamportClient(REST_API, REST_API_SECRET);
    }

    @PostMapping("/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession httpSession,
                                                    @PathVariable String imp_uid) throws IamportResponseException, IOException {

        return iamportClient.paymentByImpUid(imp_uid);
    }
}
