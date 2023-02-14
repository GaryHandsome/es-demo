// 第三：创建Vue实例
let vm = new Vue({
    // 1.绑定挂载点
    el:'#app',

    // 2.定义数据 - 数据库 - ajax
    data:{
        productList:[]
    },

    // 生命周期函数（钩子函数）
    created() {
        // let that = this ;

        // 发起异步请求，拉取数据，赋值到data选项定义的变量
        $.get("QueryAllServlet",function( list ){
            vm.productList = list ;
        }) ;
    }
}) ;