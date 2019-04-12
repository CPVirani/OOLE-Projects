package JavaGenerator;

import ProdCollection.Lists;
import com.mifmif.common.regex.Generex;

import java.util.ArrayList;
import java.util.Vector;


public class Generator {
    NodeGen<String> t_rootNode;
    NodeGen<String> t_currentNode;
    Vector<NodeGen<String >> leafNodes = new Vector<>();


    public Generator(String p_root) {
        t_rootNode = new NodeGen<String>(p_root);
        t_currentNode=t_rootNode;
    }
    boolean isRegex(String p_input){
        if(p_input.contains("regex"))
            return true;
        else
            return false;
    }
    public void addChildNodes(NodeGen<String>p_Node,int p_recDepth){
        String key = p_Node.getData();
        int production_rule;
        String production=null;
        IntGenerator rand;
        Integer[] value;
        Boolean flag=false;
        if (Lists.prodLHS.get(key) != null) {
            value = Lists.prodLHS.get(key);
            rand = new IntGenerator();
            while (!flag){
                flag=true;
                production_rule = rand.random(value[0], value[1]);
                production = Lists.prodRHS.get(production_rule);
                if (p_recDepth >= 3) {
                    if (production.contains(key)) {
                        flag = false;
                    }
                }
            }
            String nodes[] = production.split("\"");
            for (String str : nodes) {
                p_Node.addChild(str);
            }
        }
        else if(isRegex(key)){
            key=key.substring(5,key.length());
            Generex g = new Generex(key); //give the regex as parameter here
            String str = g.random();
            p_Node.addChild(str);

        }
        else
            return;

    }
    public void getTree(NodeGen<String>p_node,int p_recdepth)
    {
        for (NodeGen<String> stringNode : p_node.getChildren()) {
            addChildNodes(stringNode,p_recdepth);
            if(stringNode.getData().equals(p_node.getData()))
                getTree(stringNode,++p_recdepth);
            else
                getTree(stringNode,p_recdepth);
        }
    }



    public void getAllLeafNodes(NodeGen<String>p_node) {

        if (p_node.getChildren().isEmpty()) {
            leafNodes.add(p_node);
        } else {
            for (NodeGen<String> child : p_node.getChildren()) {
                getAllLeafNodes(child);
            }
        }
    }
    public void codeGenerator() {

        addChildNodes(t_currentNode,0);
        getTree(t_currentNode,0);
        getAllLeafNodes(t_rootNode);
        for (NodeGen<String>str:leafNodes) {
            System.out.print(str.getData());
        }
        System.out.println();

    }
}
