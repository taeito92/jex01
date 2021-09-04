<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Read Page</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard v1</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->


    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <!-- left column -->
                <div class="col-md-12">
                    <!-- general form elements -->
                    <div class="card card-primary">
                        <div class="card-header">
                            <h3 class="card-title">Board Read</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <div class="card-body">
                            <div class="form-group">
                                <label for="exampleInputEmail10">BNO</label>
                                <input type="text" name="bno" class="form-control" id="exampleInputEmail10" placeholder="blank bno" value="<c:out value="${boardDTO.bno}"></c:out>" readonly>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Title</label>
                                <input type="text" name="title" class="form-control" id="exampleInputEmail1" placeholder="blank title" value="<c:out value="${boardDTO.title}"></c:out>" readonly>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail2">Writer</label>
                                <input type="text" name="writer" class="form-control" id="exampleInputEmail2" placeholder="blank writer" value="<c:out value="${boardDTO.writer}"></c:out>" readonly>
                            </div>
                            <div class="row">
                                <div class="col-sm-12"> <!-- 6은 절반 12는 전체 -->
                                    <!-- textarea -->
                                    <div class="form-group">
                                        <label>Textarea</label>
                                        <textarea name="content" class="form-control" rows="3" placeholder="Enter ..." disabled><c:out value="${boardDTO.content}"></c:out></textarea> <!-- textarea는 공백조심 -->
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- /.card-body -->

                        <div class="card-footer">
                            <button type="button" class="btn btn-default btnList" style="float:left">목록</button>
                            <button type="button" class="btn btn-info btnMod" style="float:right">수정/삭제</button>
                        </div>

                    </div>
                    <!-- /.card -->
                </div>
            </div>
        </div>
    </section>

</div>

<form id="actionForm" action="/board/list" method="get">
    <input type="hidden" name="page" value="${pageRequestDTO.page}">
    <input type="hidden" name="size" value="${pageRequestDTO.size}">

    <!-- 검색조건 따라 붙게하는 코드 -->
    <c:if test="${pageRequestDTO.type != null}">
        <input type="hidden" name="type" value="${pageRequestDTO.type}">
        <input type="hidden" name="keyword" value="${pageRequestDTO.keyword}">
    </c:if>
</form>

<%@include file="../includes/footer.jsp"%>

<script>

    const actionForm = document.querySelector("#actionForm");

    document.querySelector(".btnList").addEventListener("click" ,() => {
        actionForm.submit();
    }, false) //btnList에 page와 size를 물고오기 위해

    document.querySelector(".btnMod").addEventListener("click", () => {

        const bno = '${boardDTO.bno}';
        //수정하려면 bno가 필요함

        actionForm.setAttribute("action", "/board/modify")
        actionForm.innerHTML += `<input type='hidden' name='bno' value='\${bno}'>`
        actionForm.submit();
    })

</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/resources/js/reply.js"> //js 로딩 </script>

<script>

    function after(result) {
        console.log("after..........");
        console.log(result)
    }

    //reply.js를 로딩하면서 doA와 doB를 가져옴
    //doA만 부른다면 promise(약속어음) 형식으로 돌아온다.
    //promise를 다시 doA로 출력시키려면 then을 이용해야 함.(그 안에는 함수가 들어감)
    //doA().then(result => console.log(result));

    //doB(after)

    //axios로 보냈다면 json으로 data가 왔을 것임. java 객체로 보냈다고 하더라도.
    //const reply = {bno:230, replyer:'user00',reply:'22222222'}

    //doC(reply).then(result => console.log(result))

    //delete
    //doD(112).then(result => console.log(result))

    //put
    const reply = {rno:112, reply:"Update reply text...."}
    doE(reply).then(result => console.log(result))


</script>
</body>
</html>

