<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="title">
		<title>useInfo</title>
	</layout:put>
	<layout:put block="contents">
		<div class="header">
		    <h2>사용법</h2>
		</div>

		<div class="content">
		
		    <h4>과목요건설정</h4>
		    <div>
		        교양, 전공, 자유선택 각각의 총 졸업요구 이수학점들을 입력하세요.<br />
		        과목구분에는 교양, 전공, 연계전공, 부전공, 복수전공, 전공심화, 교직이 있습니다.<br />
		        <img src="../resources/img/category.PNG" />
		    </div>
		    
		    <hr />
		
		    <h4>과목유형요건 설정</h4>
		    <div >
		        교양의 경우에는 핵심교양, 일반교양, 공통교양의 졸업요구 이수학점,<br />
		        전공, 자유선택(전공심화, 복수전공)의 경우에는 핵심전공 심화전공의 졸업요구 이수학점을 입력하세요.<br />
		        <img src="../resources/img/type.PNG" />
		    </div>
		
		    <hr />
		
		    <h4>영역요건 설정</h4>
		    <div>
		        이수해야하는 핵심교양 영역수를 입력하세요<br />
		        (14학번의 경우 1~4영역 중 3개영역 필수 이수이므로 3이라고 입력합니다.)<br />
		        <img src="../resources/img/section.PNG" />
		    </div>
		    <hr />
		
		    <h4>총학점 요건 설정</h4>
		    <div>
		        이수해야하는 총 졸업 학점을 입력하세요.<br />
		        <img src="../resources/img/score.PNG" />
		    </div>
		    <hr />
		
		    <h4>강의 등록</h4>
		    <div>
		        현재까지 수강한 강좌정보를 입력한다.(과목, 과목유형, 영역(핵심교양의 경우), 학점, 성적, 과목명)<br />
		        <img src="../resources/img/register.PNG" />
		    </div>
		    <hr />
		
		    <h4>결과확인</h4>
		    <div>
		        등록된 강의들의 정보를 통해 현재 이수한 학점들을 표시하고,<br />
		        앞에 등록한 요건을 만족하지 못했으면 몇 학점이 부족한지 알려주고 옆에 빨간색으로 표시하여 보여줍니다.<br />
		        <img style="width:80%;"  src="../resources/img/fail.PNG" /><br />
		        <p></p>
		        요건을 만족하면 몇 학점이 초과되었는지 알려주고 옆에 초록색으로 표시하여 보여줍니다.
		        <img style="width:80%;"  src="../resources/img/pass.PNG" /><br />
		        <p></p>
		        모든 요건이 다 충족되어 모두 옆에 초록색으로 표시되면 아래 부분에 Congratulation그림이 나타납니다,<br />
		        즉, 모든 졸업요건을 만족시켰다는 의미입니다.<br />
		        <img style="width:50%;" src="../resources/congratulation.jpg " />
		    </div>
		    <hr />
		
		    <h4>회원관리</h4>
		    <div>
		        비밀번호 변경하기(현재 비밀번호를 다른 것으로 바꿀 수 있습니다.)<br />
		        회원탈퇴(탈퇴를 할 수 있습니다. 그러나 탈퇴한 이메일로 다시 회원가입할 수는 없습니다.)<br />
		        <img src="../resources/img/update.PNG" />
		    </div>
		    <p style="color:red; font-weight:bold">*TOEIC과 교직의 경우 교원자격검정기준은 고려하지 않았습니다.</p>
		
		</div>
	</layout:put>
	<layout:put block="scripts">
		<script type="text/javascript">
			$(function () {
				$("#tabs").tabs();
			});
    	</script>
    </layout:put>
</layout:extends>