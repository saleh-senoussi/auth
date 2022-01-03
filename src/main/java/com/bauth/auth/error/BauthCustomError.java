package com.bauth.auth.error;

import java.util.Map;

import javax.print.attribute.standard.DateTimeAtProcessing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BauthCustomError {
    /*private static ErrorModel error;
    private static 

    public static BauthCustomError fromDefaultAttributeMap(final String apiVersion,
                                                            final Map<String, Object> defaultErrorAttributes,
                                                            final String sendReportBaseUri) {
        // original attribute values are documented in org.springframework.boot.web.servlet.error.DefaultErrorAttributes
        return new BauthCustomError(
                ((Integer) defaultErrorAttributes.get("status")).toString(),
                (String) defaultErrorAttributes.getOrDefault("message", "no message available"),
                (String) defaultErrorAttributes.getOrDefault("error", error),
                sendReportBaseUri
        );
    }*/
}
