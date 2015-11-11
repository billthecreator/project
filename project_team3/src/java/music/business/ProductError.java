/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.business;

/**
 *
 * @author William
 */
public class ProductError {
    
    private boolean codeError = false;
    private boolean codeError2 = false;

    private boolean artistError = false;
    private boolean albumError = false;
    private boolean priceError = false;
    private boolean priceError2 = false;
    private boolean coverURLError = false;

    
    public boolean anyErrors(){
        if(codeError ||
                artistError ||
                albumError ||
                priceError ||
                priceError2 ||
                coverURLError) {
            return true;
        }
        return false;
    }

    public boolean isCodeError() {
        return codeError;
    }

    public void setCodeError(boolean codeError) {
        this.codeError = codeError;
    }

    public boolean isCodeError2() {
        return codeError2;
    }

    public void setCodeError2(boolean codeError2) {
        this.codeError2 = codeError2;
    }
    
    public boolean isArtistError() {
        return artistError;
    }

    public void setArtistError(boolean artistError) {
        this.artistError = artistError;
    }

    public boolean isAlbumError() {
        return albumError;
    }

    public void setAlbumError(boolean albumError) {
        this.albumError = albumError;
    }

    public boolean isPriceError() {
        return priceError;
    }

    public void setPriceError(boolean priceError) {
        this.priceError = priceError;
    }

    public boolean isPriceError2() {
        return priceError2;
    }

    public void setPriceError2(boolean priceError2) {
        this.priceError2 = priceError2;
    }
    
    public boolean isCoverURLError() {
        return coverURLError;
    }

    public void setCoverURLError(boolean coverURLError) {
        this.coverURLError = coverURLError;
    }
}
