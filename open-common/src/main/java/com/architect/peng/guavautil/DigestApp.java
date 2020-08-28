package com.architect.peng.guavautil;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author shangpeng
 * @version V1.0
 * @Date 2020/8/21 16:06
 */
public class DigestApp {
    public static void main(String[] args) {
        //需要对下单那一时刻的商品信息备份
        String data = "网销投连险是保险公司的一款保险产品，在互联网金融上还是很常见的。" + "比如京东天天盈，网易有钱零钱++。这些保险削弱了保险的保障功能，降低成本，从而提高保险的理财功能提高理财收益。"
                + "投连险基本和银行结构性理财产品一样，信息披露度不高，但是有保险公司兜底，不至于整个平台跑路。"
                + "投资投连险可以想象为投资一个起点低的银行理财产品吧。网销投连险一般都受益在4-6%，不承诺保本。"
                + "经常爆出保险公司的保障型长期投连险出现投资亏损新闻，但是网销短期投连险投资型投连险目前没有出现亏损，基本也能按照预期收益兑付。"
                + "网销投连险安全性和收益性都比较居中，短期产品危险系数不高，但是在债券违约的大环境下，长期产品安全性没有太大保障。" + "不过好在保险公司没有跑路风险，至少不会把本金损失殆尽啊。";

        String encodeS = DigestUtils.md5Hex(data);
        System.out.println(encodeS);
    }
}
