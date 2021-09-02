<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Register Page</h1>
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
                            <h3 class="card-title">Board Register</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <form id="formRegister">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Title</label>
                                    <input type="text" name="title" class="form-control" id="exampleInputEmail1" placeholder="Enter title">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail2">Writer</label>
                                    <input type="text" name="writer" class="form-control" id="exampleInputEmail2" placeholder="Enter Writer">
                                </div>
                                <div class="row">
                                    <div class="col-sm-12"> <!-- 6은 절반 12는 전체 -->
                                        <!-- textarea -->
                                        <div class="form-group">
                                            <label>Textarea</label>
                                            <textarea name="content" class="form-control" rows="3" placeholder="Enter ..."></textarea>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!-- /.card-body -->

                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btnWrite" style="float: right">글쓰기</button>
                                <button type="submit" class="btn btn-primary btnToList" style="float: left">목록</button>
                            </div>
                        </form>

                    </div>
                    <!-- /.card -->
                </div>
            </div>
        </div>
    </section>

</div>

<%@include file="../includes/footer.jsp"%>

<script>

    const form = document.querySelector("#formRegister")

    document.querySelector(".btnToList").addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        form.setAttribute("action","/board/list")
        form.setAttribute("method", "get") //list로 다시 보냄

        const arr = form.querySelectorAll(".form-group");

        for (let i = 0; i < arr.length; i++) {
            arr[i].remove()
        }
        form.submit();
    },false);

    document.querySelector(".btnWrite").addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        form.setAttribute("action","/board/register")
        form.setAttribute("method", "post") //실질적 글등록
        form.submit();

    },false);


</script>

</body>
</html>

