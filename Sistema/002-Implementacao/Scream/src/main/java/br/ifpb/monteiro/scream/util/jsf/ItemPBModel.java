package br.ifpb.monteiro.scream.util.jsf;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;

public class ItemPBModel extends ListDataModel<ItemProductBacklog> implements SelectableDataModel<ItemProductBacklog>{

	public ItemPBModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemPBModel(List<ItemProductBacklog> data) {
		super(data);
	}

	
	@Override
	public ItemProductBacklog getRowData(String rowKey) {
		List<ItemProductBacklog> itensPB = (List<ItemProductBacklog>) getWrappedData();
        for(ItemProductBacklog itemPB : itensPB) {
            if(itemPB.getId().equals(rowKey))
                return itemPB;
        }
        return null;
	}

	@Override
	public Object getRowKey(ItemProductBacklog itemPB) {
		return itemPB.getId();
	}

}
