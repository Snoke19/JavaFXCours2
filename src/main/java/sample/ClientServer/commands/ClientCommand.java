package sample.ClientServer.commands;

import java.io.Serializable;
import java.util.List;

public class ClientCommand implements Serializable {

    private ClientCommandTypes clientCommandType;
    private List<Object> objectList;

    public ClientCommand(ClientCommandTypes clientCommandType, List<Object> objectList) {
        this.clientCommandType = clientCommandType;
        this.objectList = objectList;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public ClientCommandTypes getClientCommandType() {
        return clientCommandType;
    }

    public void setClientCommandType(ClientCommandTypes clientCommandType) {
        this.clientCommandType = clientCommandType;
    }

//    public Object processCommand() {
//        if (clientCommandType == ClientCommandTypes.SELECT_ORDER) {
//            return selectOrders();
//        } else if (clientCommandType == ClientCommandTypes.SELECT_CLIENT_NAMES){
//            return ODBC_PubsBD.selectClientNames();
//        } else if (clientCommandType == ClientCommandTypes.READ_LAST_ORDERS){
//            return null;
//        } else {
//            throw new IllegalArgumentException("NO SUCH COMMAND");
//        }
//    }
//
//    private List<DtoOrder> selectOrders(){
//        List<DtoOrder> dtoOrdersList = ODBC_PubsBD.selectOrders();
//        dtoOrdersList.forEach(item -> {
//            item.formattingDate();
//            item.calculationDiscount();
//            item.calculationBill();
//        });
//        return dtoOrdersList;
//    }
}