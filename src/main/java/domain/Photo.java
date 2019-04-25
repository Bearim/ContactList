package domain;

public class Photo {
    private int contact_id;
    private byte[] imageData;
    private String imageFileName;

    /*public Photo(int contact_id, byte[] imageData, String imageFileName) {
        this.contact_id = contact_id;
        this.imageData = imageData;
        this.imageFileName = imageFileName;
    }

    public Photo(byte[] imageData, String imageFileName) {
        this.imageData = imageData;
        this.imageFileName = imageFileName;
    }

    public Photo() {
    }*/

    public static class Builder{
        private static Photo photo;

        public Builder(){
            photo = new Photo();
        }

        public Builder withContactId(int contactId){
            photo.contact_id = contactId;
            return this;
        }

        public Builder withImageData(byte[] imegeData){
            photo.imageData = imegeData;
            return this;
        }

        public Builder withImageFileName(String imageFileName){
            photo.imageFileName = imageFileName;
            return this;
        }

        public Photo build(){
            return photo;
        }
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
