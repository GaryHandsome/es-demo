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
        productList:[]
    },

    // 3.方法定义 - 响应事件
    methods:{
        // 删除商品
        del(pid) {
            if(confirm('您真的要删除此商品吗？')) {
                // ajax
                console.log("确定" , pid) ;

            }
        }
    },

    // 4.生命周期函数（钩子函数）
    created() {
        // let that = this ;
        // 发起异步请求，拉取数据，赋值到data选项定义的变量 - 数据驱动视图
        $.get("QueryAllServlet",function( list ){
            vm.productList = list ;
        }) ;

        // console.log(vm.productList)
    }
}) ;