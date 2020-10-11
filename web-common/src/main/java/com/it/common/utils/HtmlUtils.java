package com.it.common.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.Arrays.asList;
import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.quote;

/**
 * @author Andy wang
 */
public final class HtmlUtils {

    private static final String START_COMMENT = "<!--";
    private static final String END_COMMENT = "-->";

    private HtmlUtils() {
    }

    public static String stripTags(String input, String allowedTags) {
        return removeUnknownTags(removeHtmlComments(input), allowedTags);
    }

    public static String stripTags(String input) {
        return removeUnknownTags(removeHtmlComments(input), "");
    }

    private static String removeUnknownTags(String input, String knownTags) {
        List<String> knownTagList = asList(knownTags.replaceAll("^<", "").replaceAll(">$", "").split("><"));
        return removeTags(input, knownTagList);
    }

    private static String removeTags(String input, List<String> knownTagList) {
        Pattern tag = compile("</?([^\\s>]*)\\s*[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher matches = tag.matcher(input);
        String result = input;
        while (matches.find()) {
            if (!knownTagList.contains(matches.group(1))) {
                result = result.replaceAll(quote(matches.group()), "");
            }
        }
        return result;
    }

    private static String removeTags(String input, String startTag, String endTag) {
        String result = input;
        while (result.contains(startTag)) {
            int start = result.indexOf(startTag);
            int end = result.substring(start + startTag.length()).indexOf(endTag);

            if (end == -1)
                result = result.substring(0, start);
            else
                result = result.substring(0, start) + result.substring(start + startTag.length() + end + endTag.length());
        }

        return result;
    }

    private static String removeHtmlComments(String input) {
        return removeTags(input, START_COMMENT, END_COMMENT);
    }

    public static void main(String[] args) {
        String input = "<div style=\"max-width: 480px; padding: 0 16px; font-family: MarketSans; font-size: 14px; color: #111820; line-height: 1.5;\"><h1 style=\"padding: 24px 0 16px 0; font-weight: bold; font-size: 14px; margin: 0;\">Activity on your account didn't follow our [POLICY_NAME]. [POLICY_LONG_NAME]</h1><div><div style=\"border-bottom: 1px solid #c7c7c7; padding: 16px 0;\"><div style=\"color: #767676;\">What activity didn't follow the policy</div><div style=\"padding-top: 16px;\"><p style=\"margin: 0;\">[TDE_TNS_EVIDENCE]"
            + "<!-- <hidetokens>[TSC_BA_CLAIMS_INR_PTB1338][TSC_BA_CLAIMS_PTB1336][TSC_BA_CLAIMS_TRACKING_PTB1342][TSC_BA_REFUND_PARTIAL_PTB1354][TSC_BRM_MA_RESTRICTION_PTB1378][TSC_BRM_PP_RESTRICTION_PTB1379][TSC_BRM_CONTINUATION_PTB1380][TSC_BRM_MA_CLAIMS_INR_PTB1381][TSC_BRM_INR_GAIN_PTB1382][TSC_BRM_CLAIMS_EARLY_PTB1383][TSC_BRM_CLAIMS_CUSTOMS_PTB1384][TSC_BRM_FRAUD_CUSTOMS_PTB1385][TSC_BRM_FRAUD_SHIPPING_PTB1386][TSC_BRM_CLAIMS_CANNED_PTB1387][TSC_BRM_FRAUD_UNREASONABLE_PTB1388][TSC_BRM_FRAUD_CHARGEBACKS_PTB1389][TSC_BRM_CLAIMS_RECEIVED_PTB1390][TSC_BRM_REFUND_RETURNS_PTB1391][TSC_BRM_REFUND_MANIPULATION_PTB1392][TSC_BRM_SNAD_GAIN_PTB1393][TSC_BRM_SNAD_HIGH_REPLACE_ITEM_PTB1394][TSC_BRM_SNAD_HIGH_FAULTY_ARRIVAL_PTB1395][TSC_BRM_SNAD_HIGH_RETURNS_PTB1396][TSC_BRM_SNAD_HIGH_CF_CLAIMS_PTB1397][TSC_BRM_SNAD_HIGH_FAULTY_RETURN_PTB1398][TSC_BA_CLAIMS_MISUSE_PTB1339][TSC_BA_CLAIMS_SNAD_PTB1341][TSC_BA_CLAIMS_GAIN_PTB1337][TSC_BA_CLAIMS_RETALIATION_PTB1340][TSC_BA_REFUND_HIGH_PTB1351][TSC_BA_REFUND_ABUSE_PTB1349][TSC_BA_REFUND_RETURNS_PTB1355][TSC_BA_REFUND_EXTORTION_PTB1350][TSC_BA_FB_MOD_PTB1348][TSC_BA_FB_HIGH_PTB1347][TSC_BA_UPI_PTB1356][TSC_BRM_PATTERN_OF_RETURNS_AND_CLAIMS_PTB1418][TSC_BRM_PATTERN_OF_SNAD_RETURNS_PTB1419][TSC_BRM_PATTERN_OF_INR_CLAIMS_PTB1420]</hidetokens> -->undefined</p>undefined</div>undefined</div>undefined<div style=\"border-bottom: 1px solid #c7c7c7; padding: 16px 0;\">undefined<div style=\"color: #767676;\">What you need to do next</div>undefined<div style=\"padding-top: 16px;\">undefined<p style=\"margin: 0;\">[POLICY_NEXT_STEPS]</p>undefined</div>undefined</div>undefined<div style=\"border-bottom: 1px solid #c7c7c7; padding: 16px 0;\">undefined<div style=\"color: #767676;\">What is the policy</div>undefined<div style=\"padding-top: 16px;\">undefined<p style=\"margin: 0;\">[POLICY_PLACEHOLDER]</p>undefined</div>undefined</div>undefined<div style=\"border-bottom: 1px solid #c7c7c7; padding: 16px 0;\">undefined<div style=\"color: #767676;\">How this affects your account</div>undefined<div style=\"padding-top: 16px;\">undefined<p style=\"margin: 0;\">[DP_POLICY_ACTIONS]</p>undefined</div>undefined</div>undefined<div style=\"border-bottom: 1px solid #c7c7c7; padding: 16px 0;\">undefined<div style=\"color: #767676;\">Why we have this policy</div>undefined<div style=\"padding-top: 16px;\">undefined<p style=\"margin: 0;\">[POLICY_REASON]</p>undefined</div>undefined</div>undefined<div style=\"border-bottom: 1px solid #c7c7c7; padding: 16px 0;\">undefined<div style=\"color: #767676;\">More information and help</div>undefined<div style=\"padding-top: 16px;\">undefined<p style=\"margin: 0;\">[POLICY_URL]</p>undefined</div>undefined</div>undefined<div style=\"padding: 16px 0;\">undefined<br />We appreciate your understanding.undefined</div>undefined</div></div>;";
        System.out.println(stripTags(input));
    }
}
