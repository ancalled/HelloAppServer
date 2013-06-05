package kz.helloapp.test.robokassa;


public class ApiTest {


    public static final String URL_TEMPL = "http://test.robokassa.ru/Index.aspx?" +
            "MrchLogin=%s&" +
            "OutSum=%d&" +
            "InvId=%s&" +
            "Desc=%s&" +
            "SignatureValue=%s";

    public static void main(String[] args) {

        String merchant = "parfenad";

        String pass1 = "bykCs2qW";
        String pass2 = "dVwfP81a";

        String myurl = "helloapp.microcosmus.net/helloapp/customer/payment-result";


        int price = 3400;
        String account = "A0041";
        String descr = "descr";


        String sign = "dsd";

        String url = String.format(URL_TEMPL, merchant, price, account, descr, sign);






//        MrchLogin=sMerchantLogin&
//                OutSum=nOutSum&
//                InvId=nInvId&
//                Desc=sInvDesc&
//                SignatureValue=sSignatureValue
//        IncCurrLabel=sIncCurrLabel&
//                Culture=sCulture



    }

}
