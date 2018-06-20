<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
		    <h2>강의수정</h2>
		</div>
		<div class="content">
    	<form class="pure-form pure-form-aligned" id="lectureUpdateFrm" method="post" action="/lecture/update/${lectureDetail.id}">
        <fieldset>
            <div class="pure-control-group">
                <label for="category">과목구분</label>
                <select id="category" name="category" required>
                        @foreach (CanYou.Models.Info.LectureCategory.LectureCategoryItem categoryItem in ViewBag.categoryList)
                        {
                            string selected = item.LectureCategoryId == categoryItem.Id ? "selected" : "";
                        
                            <option value="@categoryItem.Id" @selected>@categoryItem.Name</option>
                        }
                    
                </select>
            </div>

            <div class="pure-control-group">
                <label for="type">과목유형구분</label>
                <select id="type" name="type">
                    @if (ViewBag.typeList.Count == 0)
                    {
                        <option value="0">구분없음</option>
                    }
                    else
                    {
                        foreach (CanYou.Models.Info.LectureType.LectureTypeItem typeItem in ViewBag.typeList)
                        {
                            string selected = item.LectureTypeId == typeItem.Id ? "selected" : "";
                            <option value="@typeItem.Id" @selected>@typeItem.Name</option>
                        }
                    }
                    
                </select>
            </div>

            <div class="pure-control-group">
                <label for="section">영역</label>
                <select id="section" name="section">
                    @if (ViewBag.sectionList.Count == 0)
                    {
                        <option value="0">구분없음</option>
                    }
                    else
                    {
                        foreach (CanYou.Models.Info.Section.SectionItem sectionItem in ViewBag.sectionList)
                        {
                            string selected = item.SectionId == sectionItem.Id ? "selected" : "";
                            <option value="@sectionItem.Id" @selected>@sectionItem.Name</option>
                        }
                    }
                    
                </select>
            </div>


            <div class="pure-control-group">
                <label for="credit">학점</label>
                <input id="creit" name="credit" type="text" placeholder="숫자를 입력하세요." value="@item.Credit" required>
            </div>

            <div class="pure-control-group">
                <label for="score">성적</label>
                <input id="score" name="score" type="text" placeholder="받은 성적을 입력하세요" value="@item.Score" required />
            </div>

            <div class="pure-control-group">
                <label for="title">교과목명</label>
                <input id="title" name="title" type="text" placeholder="교과목명을 입력하세요." value="@item.Name" required>
            </div>
            <div class="pure-controls">
                <button class="pure-button pure-button-primary" id="updateBtn">수정</button>
                <button class="pure-button pure-button-primary" id="cancel">취소</button>
            </div>
        </fieldset>
    </form>
</div>


@section script
{
<script src="~/Scripts/jquery.validate.min.js"></script>
<script src="~/Scripts/jquery.form.js"></script>
<script src="~/Content/js/customValidator.js"></script>
<script type="text/javascript">
    

    $("#lectureUpdateFrm").validate({
        rules: {
            credit: {
                required: true
            },
            score:
              {
                  required: true,
                  scoreValue:true
              },
            title: {
                required: true
            }
        },
        messages: {
            credit: {
                required: "학점을 입력해주세요"
            },
            title: {
                required: "교과목명을 입력해주세요"
            },
            score: {
                required: "받은 점수를 입력해주세요"
            }
        }
    });

    $("#updateBtn").click(function () {
        if (!$("#lectureUpdateFrm").valid()) return;

        $("#lectureUpdateFrm").ajaxForm({
            success: function (data, statusText, xhr, $form) {
                if (data.result == 'fail') {
                    alert(data.message);
                } else {
                    $(location).attr('href', "/Lecture/List");
                }
            },
            dataType: 'json'
        });
        $("#lectureUpdateFrm").submit();
        return false;
    });
    $("#cancel").click(function () {
        var url = "/Lecture/List";
        $(location).attr('href', url);
        return false;
    });
    $("#category").change(function () {
        loadtype();
        loadSection();
    });

    $("#type").change(function () {
        loadSection();
    });

    function loadtype() {
        var categoryId = $("#category").val();
        var url = "/Requirement/LectureTypePartial?categoryId=" + categoryId;
        $("#type").load(url, {}, function () {
            loadSection();
        });
    }

    function loadSection() {
        var typeId = $("#type").val();
        var url = "/Lecture/SectionPartial?typeId=" + typeId;
        $("#section").load(url);
    }
</script>
}

