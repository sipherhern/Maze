<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="layout.tld" prefix="e"%>

<e:override name="title">股东汇 | 管理平台</e:override>


<e:override name="content_right">


	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a href="index.html">Home</a> <i
			class="icon-angle-right"></i></li>
		<li><i class="icon-edit"></i> <a href="#">Forms</a></li>
	</ul>

	<div class="box-header">
		<h2>
			<i class="halflings-icon align-justify"></i><span class="break"></span><strong>第二步
			</strong>填写商品基本信息
		</h2>
	</div>
	<div class="row-fluid">
		<div class="box span12">
			<br>
			<form action="" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="control-label"></label> <input type="text"
						class="span5 form-control" placeholder="输入商品类别进行搜索">
					<button type="submit" class="btn btn-default"><i class="halflings-icon search"></i></button>
					<label for="consume-tip" class="control-label"></label>
				</div>
			</form>
			<hr>

			<form class="form-horizontal"
				action='<c:url value="/merchant/product/success.jsp"/>' method="get">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="typeahead">宝贝标题 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" id="typeahead"
								data-provide="typeahead" data-items="4"
								data-source='["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"]'>

						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="typeahead">宝贝数量 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" id="typeahead"
								data-provide="typeahead" data-items="4"
								data-source='["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"]'>

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="typeahead">商品条形码 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" id="typeahead"
								data-provide="typeahead" data-items="4"
								data-source='["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"]'>

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="typeahead">宝贝价格 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" id="typeahead"
								data-provide="typeahead" data-items="4"
								data-source='["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"]'>

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="typeahead">宝贝价格 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" id="typeahead"
								data-provide="typeahead" data-items="4"
								data-source='["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"]'>

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="starttime">上市时间</label>
						<div class="controls">
							<input type="text" class="input-xlarge datepicker" id="starttime"
								value="08/10/15">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="endtime">结束时间</label>
						<div class="controls">
							<input type="text" class="input-xlarge datepicker" id="endtime"
								value="02/16/16">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">宝贝规格 </label>
						<div class="box span9">
							<div class="control-group">
								<div class="span12">
									<br>
									<div class="control-group">
										<label class="control-label">颜色</label>
										<div class="controls">
											<label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox1" value="option1">
												Option 1
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox2" value="option2">
												Option 2
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox3" value="option3">
												Option 3
											</label> <br> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox4" value="option4">
												Option 4
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox5" value="option5">
												Option 5
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox6" value="option6">
												Option 6
											</label>
										</div>
									</div>
								</div>
							</div>

							<div class="control-group">
								<div class="span10">
									<div class="control-group">
										<label class="control-label">尺寸</label>
										<div class="controls">
											<label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox1" value="option1">
												Option 1
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox2" value="option2">
												Option 2
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox3" value="option3">
												Option 3
											</label> <br> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox4" value="option4">
												Option 4
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox5" value="option5">
												Option 5
											</label> <label class="checkbox inline"> <input
												type="checkbox" id="inlineCheckbox6" value="option6">
												Option 6
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="typeahead">宝贝图片</label>
						<div class="box span9">
							<div class="controls">
								<input type="text" class="span6 typeahead" id="typeahead"
									data-provide="typeahead" data-items="4"
									data-source='["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"]'>
								<p class="help-block">Start typing to activate auto
									complete!</p>
							</div>
						</div>
					</div>


					<div class="control-group hidden-phone">
						<label class="control-label" for="textarea2">宝贝描述</label>
						<div class="controls">
							<textarea class="cleditor" id="textarea2" rows="3"></textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"></label>
						<div class="span9">
							<button type="submit" class="btn btn-primary btn-lg">Save
								changes</button>
							<button class="btn btn-primary btn-lg">Cancel</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>


</e:override>





<%@include file="/merchant/main.jsp"%>