pragma solidity 0.4.8;

contract TransferContract {
    // �¼�
    event Transfer(address indexed from, address indexed to, uint value);
    // ����
    function TransferFunc(address from, address to, uint value) {
        Transfer(from, to, value);
    }
}