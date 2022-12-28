// 集中导出与逐个导出的混合使用

// 1.逐个导出
export let m = 200 ;

let obj = {
    name:'ww',
    age:38
} ;

function fun() {
    console.log('你好')
}

// 2.集中导出 - 注意：集中导出必须使用大括号，哪怕导出的只有一个内容，也必须加大括号
export {obj,fun}
