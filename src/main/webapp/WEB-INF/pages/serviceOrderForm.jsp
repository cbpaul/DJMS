<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="serviceOrderDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='serviceOrderDetail.heading'/>"/>
    <script type="text/javascript">
    	function customTypeChange(node){
    		if($(node).val()=='MERCHANT'){
    			$("#common").remove();
    			$("#myModalLabel").text("添加商家");
    			$("option[value='MERCHANT']").attr("selected",true);
    			$(node).parent("div").parent("fieldset")
    			.after('<fieldset id="merchant" class="control-group"><label class="control-label" for="serviceOrderForm_customer_type"> 选择商家: </label>\
    					<div class="controls">\
    					<select id="merchant_selecte" name="serviceOrder.customer.id">\
    					<option value="">请选择...\
    					<c:forEach items="${businesses }" var="business">\
    						<option id="merchant${customer.id}"  value="${business.id }">${business.name } &nbsp;:&nbsp ${business.phoneNum}\
    					</c:forEach>\
    					</select><a role="button" class="btn" data-toggle="modal" href="#myModal" >\
    		            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>\
   		       		 </a>\
    					</div>\
    					</fieldset>'
    			);
    		}else{
    			$("#merchant").remove();
    			$("#myModalLabel").text("添加用户");
    			$("option[value='COMMON']").attr("selected",true);
    			$(node).parent("div").parent("fieldset")
    			.after('<fieldset id="common" class="control-group"><label class="control-label" for="serviceOrderForm_customer_type"> 普通用户： </label>\
    					<div class="controls">\
    					<select id="common_selecte" name="serviceOrder.customer.id">\
    					<option value="">请选择...\
    					<c:forEach items="${commonCustomers }" var="customer">\
    						<option id="common${customer.id}" value="${customer.id }">${customer.name } &nbsp;:&nbsp ${customer.phoneNum}\
    					</c:forEach>\
    					</select><a role="button" class="btn" data-toggle="modal" href="#myModal" >\
    		            	<i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>\
    		       		 </a>\
    		       		 </div>\
    					</fieldset>'
    			);
    		}
    	}
    	function submitAjaxForm(formId){
    		var form = $("#"+formId);
    		$.ajax({
    			  type: "POST",
    			  url: form.attr("action"),
    			  processData:true,
    			  data:form.serialize(),
    			  dataType:"json",
    			  success: function(data){
    				if(data.type=="COMMON"){
	    				if($("#common"+data.id).size()>0){
	    					$("#common"+data.id).attr("selected",true);
	    				}else{
	    					$("#common_selecte").append("<option id=common"+data.id+" selected=selected value="+data.id+">"+data.name+" &nbsp;:&nbsp"+data.phoneNum);
	    				}
    				}
    				if(data.type=="MERCHANT"){
    					if($("#merchant"+data.id).size()>0){
    						$("#merchant"+data.id).attr("selected",true);
    					}else{
    						$("#merchant_selecte").append("<option id=merchant"+data.id+" selected=selected value="+data.id+">"+data.name+" &nbsp;:&nbsp"+data.phoneNum);
    					}
    				}
    				$("#myModal").modal("hide")
    			  }
    			 });
    	}
    </script>
</head>

<c:set var="delObject" scope="request"><fmt:message key="serviceOrderList.serviceOrder"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="serviceOrderDetail.heading"/></h2>
    <fmt:message key="serviceOrderDetail.message"/>
</div>

<div class="span7">
    <s:form id="serviceOrderForm" action="saveServiceOrder" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="serviceOrder.id"/>
        <s:select name="serviceOrder.brand.id" list="brandList" listKey="id" listValue="name" key="select.brand"/>
        <s:select name="customer.type" list="customerTypeList" listKey="name()" listValue="name" key="select.customertype" onchange="customTypeChange(this)" headerKey="" headerValue="请选择..."/>
        <s:textfield key="serviceOrder.description" required="false" maxlength="255" />
        <s:textfield key="serviceOrder.fault" required="false" maxlength="255" />
        <s:textfield key="serviceOrder.price" required="false" maxlength="255" />
		<s:select name="serviceOrder.status" list="statusList" listKey="name()" listValue="name" key="serviceOrder.status"/>
        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty serviceOrder.id}">
                <s:submit type="button" cssClass="btn btn-warning" method="delete" key="button.delete"
                    onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel"></h3>
	</div>
	<div class="modal-body">
		 <s:form id="customerForm" action="saveCustomerAjax" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="customer.id"/>
	        <s:textfield key="customer.name" required="false" maxlength="255" />
	        <s:textfield key="customer.phoneNum" required="false" maxlength="255" />
	         <s:select name="customer.gender" list="genderList" listKey="name()" listValue="name" key="customer.gender"/>
	         <div style="display:none;">
	       		 <s:select  name="customer.type" list="customerTypeList" listKey="name()" listValue="name" key="select.customertype"/>
	        </div>
   		 </s:form>
	</div>
		<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true"><fmt:message key="button.cancel"/></button>
   		<button class="btn btn-primary"  onclick="submitAjaxForm('customerForm')"> <fmt:message key="button.save"/></button>
	</div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['serviceOrderForm']).focus();
    });
</script>
