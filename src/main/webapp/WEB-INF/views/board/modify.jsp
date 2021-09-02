<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Modify Page</h1>
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
                            <h3 class="card-title">Board Modify</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <form id="form1">
                            <input type="hidden" name="page" value="${pageRequestDTO.page}">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail10">BNO</label>
                                    <input type="text" name="bno" class="form-control" id="exampleInputEmail10" placeholder="blank bno" value="<c:out value="${boardDTO.bno}"></c:out>" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Title</label>
                                    <input type="text" name="title" class="form-control" id="exampleInputEmail1" placeholder="blank title" value="<c:out value="${boardDTO.title}"></c:out>">
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
                                            <textarea name="content" class="form-control" rows="3" placeholder="Enter ..."><c:out value="${boardDTO.content}"></c:out></textarea> <!-- textarea는 공백조심 -->
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!-- /.card-body -->

                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btnList" style="float:left">목록</button>
                                <button type="submit" class="btn btn-danger btnDel" style="float:right">삭제</button>
                                <button type="submit" class="btn btn-warning btnMod" style="float:right">수정</button>
                            </div>
                        </form>
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
</form>

<%@include file="../includes/footer.jsp"%>

<script>

    const form = document.querySelector("#form1")
    const actionForm = document.querySelector("#actionForm");

    document.querySelector(".btnList").addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();
        /*
        form.setAttribute("action","/board/list")
        form.setAttribute("method", "get")

        const arr = form.querySelectorAll(".form-group");

        for (let i = 0; i < arr.length; i++) {
            arr[i].remove()
        }
        form.submit();

        위에 코드를 jQuery로 표현하면 이렇게 표현이 가능해짐
        window.location="/board/list";
        */

        actionForm.submit();

    },false);

    document.querySelector(".btnDel").addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        form.setAttribute("action","/board/remove")
        form.setAttribute("method", "post")
        form.submit();

    },false);

    document.querySelector(".btnMod").addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        form.setAttribute("action","/board/modify")
        form.setAttribute("method", "post")
        form.submit();

    },false);

</script>

</body>
</html>

