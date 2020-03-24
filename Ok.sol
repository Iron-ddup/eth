pragma solidity ^0.4.0;

contract Ok
{
    // 数据
    string data;
    //mapping
    mapping(string =>string) private testData;
    // 获取
    function get() public constant returns (string) {
        return data;
    }
    // 设置
    function set(string x) public {
        data = x;
    }
    //setData给mapping赋值
    function setData(string a,string b) public {
        testData[a] = b;
    }
    //getData mappingde的zhi值
    function getData(string a)public constant returns(string){
        return testData[a];
    }
}