<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="mainPageTag" tagdir="/WEB-INF/tags/main-page" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>

<div class="agile-main-top">
        <div class="container-fluid">
            <c:if test="${not empty userBean}">
                <mainPageTag:user-registered/>
            </c:if>
            <c:if test="${empty userBean}">
                <mainPageTag:user-not-registered/>
            </c:if>
        </div>
    </div>