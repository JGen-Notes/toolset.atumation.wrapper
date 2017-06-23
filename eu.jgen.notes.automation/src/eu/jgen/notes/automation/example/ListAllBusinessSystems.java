package eu.jgen.notes.automation.example;

import com.ca.gen.jmmi.schema.ObjTypeCode;
import com.ca.gen.jmmi.schema.ObjTypeHelper;
import com.ca.gen.jmmi.schema.PrpTypeCode;
import com.ca.gen.jmmi.schema.PrpTypeHelper;

import eu.jgen.notes.automation.wrapper.JGenEncyclopedia;
import eu.jgen.notes.automation.wrapper.JGenFactory;
import eu.jgen.notes.automation.wrapper.JGenModel;
import eu.jgen.notes.automation.wrapper.JGenObject;

public class ListAllBusinessSystems {

	public static void main(String[] args) {
		// create instance of the factory class
		JGenFactory factory = JGenFactory.eINSTANCE;

		// create instance of the encyclopedia
		JGenEncyclopedia ency = factory.createEncyclopedia();

		// connect to the encyclopedia (model needs to be opened in the toolset
		// already)
		ency.connect();

		// select model for processing
		JGenModel genModel = ency.findModels()[0];
		System.out.println(genModel.getName());

		// find all business system in the model
		JGenObject[] bussyss = genModel.findTypeObjects(ObjTypeHelper.getCode(ObjTypeCode.BUSSYS));
		for (JGenObject bussys : bussyss) {
			System.out.println(bussys.findTextProperty(PrpTypeHelper.getCode(PrpTypeCode.NAME)));
		}
	}

}
