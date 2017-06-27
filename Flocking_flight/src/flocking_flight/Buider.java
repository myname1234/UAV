package flocking_flight;


import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.SimpleCartesianAdder;

public class Buider implements ContextBuilder<Object>{

	@Override
	public Context build(Context<Object> context) {
		// TODO Auto-generated method stub
		

		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder
				.createContinuousSpaceFactory(null);
		
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace(
				"space", context, new SimpleCartesianAdder(),
				new repast.simphony.space.continuous.StrictBorders(), Define.spaceWidth, Define.spaceHeight);
		
		Controller controller = new Controller(context, space);
		context.add(controller);
		space.moveTo(controller, 0, 0);
		
		
		return context;
		
	}

}
