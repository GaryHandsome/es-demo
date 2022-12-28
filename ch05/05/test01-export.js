// 默认导出 - export default 变量|对象|函数 ;
// 默认导入 - import 名称 from '路径+文件'

function sayHello() {
    console.log("你好") ;
}

let m = 100 ;

let obj = {
    name:'zs',
    age:18
}

// 在当前模块文件中，默认导出的是sayHello这个函数 - 注意：每个模块只允许默认导出一个成员
// 1.以非匿名的方式 - 默认导出相关的内容
// export default sayHello ;
// export default m;
// export default obj;

// 2.以匿名的方式 - 默认导出相关的内容
export default function () {
    console.log("大家好")
}

// export default 200 ;
//
// export default {
//     name:'ls',
//     age:28
// }
