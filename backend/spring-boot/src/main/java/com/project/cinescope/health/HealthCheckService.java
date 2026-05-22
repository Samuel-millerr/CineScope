package com.project.cinescope.health;

import com.project.cinescope.health.response.HealthCheckResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HealthCheckService {
    private final RequestMappingHandlerMapping handlerMapping;

    public HealthCheckService(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    public HealthCheckResponseDto healthCheck(Class<?> myClass) {
        List<Map<String, String>> routes = new ArrayList<>();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMapping.getHandlerMethods().entrySet()) {
            RequestMappingInfo mappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();

            if (handlerMethod.getBeanType().equals(myClass)) {
                String url = mappingInfo.getDirectPaths().toString();
                String httpMethod = mappingInfo.getMethodsCondition().getMethods().toString();

                routes.add(Map.of(
                        "url", url,
                        "httpMethod", httpMethod,
                        "controller", handlerMethod.getBeanType().getSimpleName(),
                        "methodName", handlerMethod.getMethod().getName()
                ));
            }
        }
        return new HealthCheckResponseDto(
                "OK",
                myClass.getSimpleName(),
                routes.size(),
                routes,
                java.time.Instant.now()
        );
    }
}
