package DOANCUOIKI.quanly;

import java.util.ArrayList;
import java.util.List;

import DOANCUOIKI.SanPham;

public class QuanLySanPham implements IQuanLy<SanPham> {

    private List<SanPham> list;
    private static QuanLySanPham instance;

    public QuanLySanPham() {
        list = new ArrayList<>();
    }



    @Override
    public void Them(SanPham obj) {
        list.add(obj);
    }

    @Override
    public void Sua(int id, SanPham obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void Xoa(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<SanPham> TimKiem(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void LoadFile() {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void XuatDanhSach() {
        // TODO Auto-generated method stub
        
    }



    @Override
    public List<SanPham> LayList() {
        // TODO Auto-generated method stub
        return null;
    }


    
}
