package org.cubeit.cube_api.dao;

import java.util.List;

import org.cubeit.cube_api.model.Cube;
import org.cubeit.cube_api.model.CubeContent;
import org.cubeit.cube_api.model.CubeShare;

public interface CubeDAO {

	public Cube insertCube(Cube cube);

	public CubeContent addContent(long userId, long cubeId, CubeContent cubeContent);

	public void removeContent(long cubeId, long contentId);

	public void deleteCube(long cubeId);

	public Cube findCube(long userId, long cubeId);

	public List<Cube> findAllCubes(long userId);

	public CubeShare shareCube(CubeShare cubeShare);

}
