
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 添加和编辑 -->

<div class="modal fade in" id="courseReviewEdit" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:550px;">
		<form class="form-horizontal" role="form" id="courseCheckInfoForm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h3 id="editLabel">课程审核信息</h3>
				</div>
				<div class="modal-body">
					<!-- 实验描述 -->
					<div class="form-group">
						<label class="col-sm-3 control-label ">不同意理由&nbsp;</label>
						<div class="col-sm-9">
							<textarea class="input-xlarge valid textarea-class" name="description" id="description" onkeydown="displayWordCound(this,500)" onkeyup="displayWordCound(this, 500)" onfocus="displayWordCound(this, 500)" placeholder="请输入不同意理由..." aria-required="true" aria-describedby="description-error" aria-invalid="false" style="height:200px;width:100%" maxlength="500"></textarea>
							<div style="float: right;margin-top: 0px;padding-right: 1px;">
								<span id="description-total" >0</span>/500
							</div>
						</div>
						
					</div>
					
					
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="submitCourseAuditFunc()" type="button">
						提交
					</button>
					<button class="btn btn-default" type="button" data-dismiss="modal">
						取消
					</button>
				</div>
			</div>
		</form>
	</div>
</div>
