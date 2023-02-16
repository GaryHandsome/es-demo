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
});


let url = "http://localhost:9999/pms/" ;


// 第三：创建Vue实例
let vm = new Vue({
    // 1.绑定挂载点
    el:'#app',

    // 2.定义数据 - 数据库 - ajax
    data:{
        productList:[],
        product:{},
        // 用于标识是否修改数据，不是修改数据就是添加数据
        isUpdate:false,
        // 修改数组元素的下标索引
        updateIndex:-1
    },


    // 3.方法定义 - 响应事件
    methods:{
        // 删除商品
        del(pid) {
            if(confirm('您真的要删除此商品吗？')) {
                // $.get("DeleteServlet","pid="+pid,function( res ){
                //     console.log("OK....")
                // },"json") ;

                $.ajax(url+"DeleteServlet", {
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

        // 添加/修改商品
        add() {
            if(vm.isUpdate==true) {
                // 修改操作
                // 第一：获取表单中的数据
                this.product.image = 'default.png' ;

                // 第二：发起异步请求
                $.post(url+"UpdateServlet",this.product,function (res){
                    // 把修改的商品数据更新到数组中，实现数据驱动页面
                    vm.productList.splice(vm.updateIndex,1,vm.product) ;

                    // 还原添加表单
                    vm.cls() ;
                }) ;

            } else {
                // 添加操作
                // 第一：获取表单中的数据
                this.product.image = 'default.png' ;

                // 第二：发起异步请求 - 如何获取表单中的数据呢？ v-model
                $.post(url+"AddServlet",this.product,function( res ){
                    if(res.code==200) {
                        // 把添加的商品数据添加到数组中，实现数据驱动页面
                        vm.productList.push(vm.product) ;

                        // 清空表单
                        vm.product = {} ;

                        // 编号文本框聚焦
                        vm.$refs.pid.focus() ;
                    }
                }) ;
            }
        } ,

        // 展示修改的商品
        show( p,index ) {
            // 创建一个新的对象，避免数据修改实时性
            let updateProduct = Object.assign({}, p)
            vm.product = updateProduct ;
            vm.isUpdate = true ;
            vm.updateIndex = index ;
            this.$refs.pid.readOnly = true;
        },

        // 清空表单，还原为添加表单
        cls() {
            this.isUpdate = false ;
            this.product = {} ;
            this.$refs.pid.readOnly = false;
            this.$refs.pid.focus();
        }
    },

    // 4.生命周期函数（钩子函数）
    created() {
        // let that = this ;
        // 发起异步请求，拉取数据，赋值到data选项定义的变量 - 数据驱动视图
        $.get(url + "QueryAllServlet",function( res ){
            vm.productList = res.data ;
        }) ;
    }
}) ;