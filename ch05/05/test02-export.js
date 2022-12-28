// 混合导出 - 默认导出与其它导出共同使用
// 1.单独导出
export let i = 100

let j = 200
let k = 300

// 2.集中导出，同时使用了默认导出
export {j as default,k}