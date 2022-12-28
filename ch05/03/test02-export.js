// 集中导出 - 统一导出 - 先定义，后集中导出

let m = 100 ;

let obj = {
    name:'ls',
    age:28
} ;

function fun() {
    console.log('你好')
}

// 集中导出 - 注意：集中导出必须使用大括号，哪怕导出的只有一个内容，也必须加大括号
export {m,obj,fun}

// 错误
// export m ;