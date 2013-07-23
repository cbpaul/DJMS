<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="purchaseList.title"/></title>
    <meta name="menu" content="PurchaseMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="purchaseList.heading"/></h2>

    <form method="post" action="${ctx}/purchases" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="purchaseList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editPurchase'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="purchases" class="table table-condensed table-striped table-hover" requestURI="" id="purchaseList" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="editPurchase" media="html"
            paramId="id" paramProperty="id" titleKey="purchase.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="purchase.id"/>
        <display:column property="count" sortable="true" titleKey="purchase.count"/>
        <display:column property="craeteTime" sortable="true" titleKey="purchase.craeteTime"/>
        <display:column property="price" sortable="true" titleKey="purchase.price"/>
        <display:column property="totalPrice" sortable="true" titleKey="purchase.totalPrice"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="purchaseList.purchase"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="purchaseList.purchases"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="purchaseList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="purchaseList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="purchaseList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
