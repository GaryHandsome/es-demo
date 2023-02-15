// 自定义全局指令 - 实现设置字体颜色
Vue.directive('color',{
    inserted(el,binding) {

        // if(binding.arg=='color') {
        //     el.style.color = binding.value ;
        // } else {
        //     el.style.background = binding.value ;
        // }

        if(!(binding.arg=='color' || binding.arg=='background')) {
            return ;
        }
        // 对象的访问有两种方法，分别为：点操作符访问和中括号访问
        el.style[binding.arg] = binding.value ;
    }
}) ;

// 定义过滤器 -实现指定货币符号和保留位数
Vue.filter('moneyFilter', function (money,type,n) {
    return type + money.toFixed(n) ;
})

// 第三：创建Vue实例
let vm = new Vue({
    // 1.绑定挂载点
    el:'#app',

    // 2.定义数据 - 数据库 - ajax
    data:{
        productList:[],
        product:{}
    },


    // 3.方法定义 - 响应事件
    methods:{
        // 删除商品
        del(pid) {
            if(confirm('您真的要删除此商品吗？')) {
                // $.get("DeleteServlet","pid="+pid,function( res ){
                //     console.log("OK....")
                // },"json") ;

                $.ajax("DeleteServlet", {
                    type: 'get',
                    data: { "pid": pid },
                    dataType: 'json',
                    success: function (res) {
                        if(res.code==200) {
                            vm.productList = vm.productList.filter((p) => {
                                return p.id != pid;
                            });
                        } else {
                            alert("删除失败")
                        }
                    },
                    error:function ( e ) {
                        console.log("error",e)
                    }
                });
            }
        },

        // 添加商品
        add() {
            // 第一：获取表单中的数据
            this.product.image = 'default.png' ;
            console.log(JSON.stringify(this.product))

            // 第二：发起异步请求 - 如何获取表单中的数据呢？ v-model
            $.post("AddServlet",this.product,function( res ){
                console.log("是否成功",res)
            }) ;
        }
    },

    // 4.生命周期函数（钩子函数）
    created() {
        console.log("created....")
        // let that = this ;
        // 发起异步请求，拉取数据，赋值到data选项定义的变量 - 数据驱动视图
        $.get("QueryAllServlet",function( res ){
            vm.productList = res.data ;
        }) ;

        // console.log(vm.productList)
    }
}) ;