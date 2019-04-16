// 在JSP页面还需要引入   <script src="http://gosspublic.alicdn.com/aliyun-oss-sdk-4.3.0.min.js"></script>

//var urlpre_value = "http://aiyusan.oss-cn-shenzhen.aliyuncs.com/";
//var region_value = "oss-cn-shenzhen";
//var accessKeyId_value = "LTAIIB4YlrWof77u";
//var accessKeySecret_value = "0a7EE2Hph3i7Wvhs9an3NpMZ7gPqzA";
//var bucket_value = "aiyusan";

//var urlpre_value = "http://hxsdj.oss-cn-shenzhen.aliyuncs.com/";
//var region_value = "oss-cn-shenzhen";
//var accessKeyId_value = "LTAIBUinJpWWdmPH";
//var accessKeySecret_value = "RuxVcRWLLlUnnB0KX1zQCesYQFR2uI";
//var bucket_value = "hxsdj";
var urlpre_value = "https://hyuu.oss-cn-shenzhen.aliyuncs.com/";
var region_value = "oss-cn-shenzhen";
var accessKeyId_value = "LTAImZLKikx0GOur";
var accessKeySecret_value = "nIm3FQzUw5HqU42WOPG5XVnfUV37Dw";
var bucket_value = "hyuu";

var client = new OSS.Wrapper({
	region : region_value,
	accessKeyId : accessKeyId_value,
	accessKeySecret : accessKeySecret_value,
	bucket : bucket_value
});
