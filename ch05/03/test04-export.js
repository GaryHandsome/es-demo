// 重命名导出
export let i = 100
let j = 200

let add = function(a,b){
    console.log(a+b)
    // ....
}

// 导出add函数，并重命名对暴露的接口名称为plus
// 注意：外部只能以plus而不能用add访问此暴露的接口了
export {j,add as plus}
