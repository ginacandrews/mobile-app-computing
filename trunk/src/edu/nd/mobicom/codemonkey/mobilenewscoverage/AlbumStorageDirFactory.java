package edu.nd.mobicom.codemonkey.mobilenewscoverage;

import java.io.File;

abstract class AlbumStorageDirFactory {
	public abstract File getAlbumStorageDir(String albumName);
}
