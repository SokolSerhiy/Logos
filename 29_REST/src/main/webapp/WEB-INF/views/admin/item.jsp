<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
	#filter>.form-group>.col-sm-12>span{
		display: block;
	}
</style>
<script>
$(function(){
	$('#category').change(function() {
		var id = $(this).val();
		if(id!=0){
			$.ajax({
				url : '/category/'+id,
				success : function(category) {
					for(var i = 0; i < category.nameOfSpecStrings.length; i++){
						var formGroup = $(document.createElement("div"));
						formGroup.addClass('form-group specString');
						formGroup.insertAfter($('.producer'));
						var label = $(document.createElement("label"));
						label.addClass('col-sm-2 control-label');
						label.html(category.nameOfSpecStrings[i].name);
						formGroup.append(label);
						var div = $(document.createElement("div"));
						div.addClass('col-sm-10');
						formGroup.append(div);
						var select = $(document.createElement("select"));
						select.addClass('form-control');
						select.attr('name', 'specificationStrings');
						div.append(select);
						for(var j = 0; j < category.nameOfSpecStrings[i].specStrings.length; j++){
							console.log(category.nameOfSpecStrings[i].specStrings[j].name);
							var option = $(document.createElement("option"));
							option.val(category.nameOfSpecStrings[i].specStrings[j].id);
							option.html(category.nameOfSpecStrings[i].specStrings[j].name);
							select.append(option);
						}
						select.chosen();
					}
					for(var i = 0; i < category.nameOfSpecDigits.length; i++){
						var formGroup = $(document.createElement("div"));
						formGroup.addClass('form-group specDigit');
						formGroup.insertAfter($('.producer'));
						var inputHidden = $(document.createElement("input"));
						inputHidden.val(category.nameOfSpecDigits[i].id);
						inputHidden.attr('name', 'specificationDigitals['+i+'].nameOfSpecificationDigital');
						inputHidden.attr('type', 'hidden');
						formGroup.append(inputHidden);
						var label = $(document.createElement("label"));
						label.addClass('col-sm-2 control-label');
						label.html(category.nameOfSpecDigits[i].name);
						formGroup.append(label);
						var div = $(document.createElement("div"));
						div.addClass('col-sm-7');
						formGroup.append(div);
						var input = $(document.createElement("input"));
						input.addClass('form-control');
						input.attr('name', 'specificationDigitals['+i+'].value');
						input.attr('placeholder', 'Enter value');
						div.append(input);
						div = $(document.createElement("div"));
						div.addClass('col-sm-3');
						formGroup.append(div);
						var select = $('#measuringSystems').clone();
						select.attr('name', 'specificationDigitals['+i+'].measuringSystems')
						div.append(select);
						select.removeAttr('style');
						select.chosen();
					}
				}
			});
		}
	});
});
</script>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/ms">Measuring system</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li><a href="/admin/nosd">Name of specification digital</a></li>
					<li><a href="/admin/noss">Name of specification string</a></li>
					<li><a href="/admin/ss">Specification string</a></li>
					<li class="active"><a href="/admin/item">Item</a><span
						class="sr-only">(current)</span></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
			<form:form class="form-horizontal" action="/admin/item" method="GET" modelAttribute="filter" id="filter">
				<custom:hiddenInputs excludeParams="search, minPrice, maxPrice, producerIds, ssIds" excludeParamsPrefix="specDigitFilters"/>
				<div class="form-group">
					<div class="col-sm-12">
	      				<form:input type="text" class="form-control" path="search" placeholder="Search"/>
	    			</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="minPrice" placeholder="Min price"/>
	    			</div>
	    			<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="maxPrice" placeholder="Max price"/>
	    			</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<form:checkboxes items="${producers}" path="producerIds" itemLabel="name" itemValue="id"/>
					</div>
				</div>
				<c:forEach items="${nosss}" var="noss">
					<div class="form-group">
						<label class="col-sm-12">${noss.name}</label>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<form:checkboxes items="${noss.specificationStrings}" itemLabel="name" itemValue="id" path="ssIds"/>
						</div>
					</div>
				</c:forEach>
				<c:forEach items="${nosds}" var="nosd" varStatus="vs">
					<form:hidden path="specDigitFilters[${vs.index}].nameId" value="${nosd.id}"/>
					<div class="form-group">
						<label class="col-sm-12">${nosd.name}</label>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<form:radiobuttons path="specDigitFilters[${vs.index}].msId" itemLabel="name" itemValue="id" items="${nosd.ms}"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-6 col-xs-6">
							<form:input type="text" class="form-control" path="specDigitFilters[${vs.index}].min" placeholder="Min value"/>
						</div>
						<div class="col-sm-6 col-xs-6">
							<form:input type="text" class="form-control" path="specDigitFilters[${vs.index}].max" placeholder="Max value"/>
						</div>
					</div>
				</c:forEach>
				<div class="form-group">
    				<div class="col-sm-12">
      					<button type="submit" class="btn btn-primary">Search</button>
    				</div>
  				</div>
			</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="category, name, producer, specificationStrings, price, measuringSystems" excludeParamsPrefix="specificationDigitals"/>
					<div class="form-group">
						<label for="category" class="col-sm-offset-2 col-sm-10"><form:errors path="category"/></label>
					</div>
					<div class="form-group">
    					<label for="category" class="col-sm-2 control-label">Category</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="category" id="category">
	    						<option value="0">Select category</option>
	    						<form:options  items="${categories}" itemLabel="name" itemValue="id"/>
    						</form:select>
    					</div>
  					</div>
					<div class="form-group">
						<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
					</div>
					<div class="form-group">
    					<label for="name" class="col-sm-2 control-label">Name</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="name" id="name"/>
    					</div>
  					</div>
  					<div class="form-group producer">
    					<label class="col-sm-2 control-label">Producer</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="producer" itemLabel="name" itemValue="id" items="${producers}"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="price" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>
					</div>
  					<div class="form-group">
  						<label class="col-sm-2 control-label">Price</label>
  						<div class="col-sm-7">
  							<form:input id="price" path="price" class="form-control"/>
  						</div>
  						<div class="col-sm-3">
  							<form:select itemLabel="name" itemValue="id" items="${measuringSystems}" path="measuringSystems" class="form-control" multiple="false" id="measuringSystems"/>
  						</div>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-offset-2 col-sm-10">
		  					<label class="btn btn-default btn-file">
		        				Browse <input type="file" name="file" style="display: none;">
		    				</label>
	    				</div>
    				</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-xs-2"><h3>Image</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Item name</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Item price</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Category</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Producer</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Update</h3>
			<h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="item">
				<div class="row">
					<div class="col-md-2 col-xs-2"><img class="img-rounded" width="100%" src="/images/item/${item.id}.jpg?version=${item.version}"></div>
					<div class="col-md-2 col-xs-2">${item.name}</div>
					<div class="col-md-2 col-xs-2">${item.price}</div>
					<div class="col-md-2 col-xs-2">${item.category.name}</div>
					<div class="col-md-2 col-xs-2">${item.producer.name}</div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/item/update/${item.id}<custom:allParams/>">update</a>
					<a class="btn btn-danger" href="/admin/item/delete/${item.id}<custom:allParams/>">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="price"/>
						<custom:sort innerHtml="Price asc" paramValue="price,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>