package layout;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sc.R;
import com.sc.ui.FragmentBase;
import com.sc.utity.UIUtils;
import com.sc.utity.Utils;

import java.util.HashMap;
import java.util.Map;

import Jama.Matrix;


public class MatrixFragment extends FragmentBase {
    TextView mat;
    TextView expr;

    public MatrixFragment() {
        // Required empty public constructor
        fragmentId = R.layout.fragment_matrix;
    }

    @Override
    public void init() {
        mat = view.findViewById(R.id.matTvMat);
        expr = view.findViewById(R.id.matTvExpr);
        //UIUtils.disableVirtualKeyboard(mat);
        view.findViewById(R.id.matBtn0).setOnClickListener(this);
        view.findViewById(R.id.matBtn1).setOnClickListener(this);
        view.findViewById(R.id.matBtn2).setOnClickListener(this);
        view.findViewById(R.id.matBtn3).setOnClickListener(this);
        view.findViewById(R.id.matBtn4).setOnClickListener(this);
        view.findViewById(R.id.matBtn5).setOnClickListener(this);
        view.findViewById(R.id.matBtn6).setOnClickListener(this);
        view.findViewById(R.id.matBtn7).setOnClickListener(this);
        //view.findViewById(R.id.matBtn8).setOnClickListener(this);
        view.findViewById(R.id.matBtn9).setOnClickListener(this);
        view.findViewById(R.id.matBtn10).setOnClickListener(this);
        view.findViewById(R.id.matBtn11).setOnClickListener(this);
        view.findViewById(R.id.matBtn12).setOnClickListener(this);
        view.findViewById(R.id.matBtn13).setOnClickListener(this);
        view.findViewById(R.id.matBtn14).setOnClickListener(this);
        view.findViewById(R.id.matBtn15).setOnClickListener(this);
        view.findViewById(R.id.matBtn16).setOnClickListener(this);
        view.findViewById(R.id.matBtn17).setOnClickListener(this);
        view.findViewById(R.id.matBtn18).setOnClickListener(this);
        view.findViewById(R.id.matBtn19).setOnClickListener(this);
        view.findViewById(R.id.matBtn20).setOnClickListener(this);
        view.findViewById(R.id.matBtn21).setOnClickListener(this);
        view.findViewById(R.id.matBtn22).setOnClickListener(this);
        view.findViewById(R.id.matBtn23).setOnClickListener(this);
        view.findViewById(R.id.matBtn24).setOnClickListener(this);
        view.findViewById(R.id.matBtn25).setOnClickListener(this);
        view.findViewById(R.id.matBtn26).setOnClickListener(this);
        view.findViewById(R.id.matBtn27).setOnClickListener(this);
        view.findViewById(R.id.matBtn28).setOnClickListener(this);
        view.findViewById(R.id.matBtn29).setOnClickListener(this);
        //
        matrix = new HashMap<>();
    }

    @Override
    public void onClick(View view){
        String t = ((Button)view).getText().toString();
        switch (t){
            case "Enter":
                mat.setText(mat.getText().toString()+"\n");
                break;
            case "Del1":
                String e = expr.getText().toString();
                if(e.length() > 0){
                    e = e.substring(0, e.length()-1);
                }
                expr.setText(e);
                break;
            case "C1":
                expr.setText("");
                break;
            case "Del2":
                deleteTvMatrix();
                break;
            case "C2":
                mat.setText("");
                break;
            case "set":
                setMatrix();
                break;
            case "solve":
                solve();
                break;
            case "rank":
                break;
            case "det":
                break;
            case "T":
                T();
                break;
            case "?":
                break;
            default:
                if(Utils.isInteger(t) || ",".equals(t)){
                    mat.setText(mat.getText().toString()+t);
                } else {
                    expr.setText(expr.getText().toString() + t);
                }
        }
    }

    private Map<String, Matrix> matrix;
    private void deleteTvMatrix(){
        String m = mat.getText().toString();
        while(true) {
            if (m.length() > 0) {
                String last = Utils.last(m);
                m = m.substring(0, m.length() - 1);
                if(!last.equals("\r") && !last.equals("\n")){
                    break;
                }
            } else {
                break;
            }
        }
        mat.setText(m);
    }
    private void setMatrix(){
        String e = expr.getText().toString();
        String name = e.split("=")[0];
        matrix.put(name, toMatrix(mat.getText().toString()));
        expr.setText(name);
    }
    private void solve(){

    }
    private void T(){
        String name = expr.getText().toString();
        Matrix m = matrix.get(name);
        m = m.transpose();
        mat.setText(toString(m));
    }
    private static Matrix toMatrix(String mat){
        int row = mat.length() - mat.replaceAll("\n","").length();
        int col = mat.split("\n")[0].split(",").length;
        double[][] m = new double[row][col];
        String[] num = mat.replace("\n",",").split(",");
        for(int i = 0, k = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                m[i][j] = Double.valueOf(num[k++]).doubleValue();
            }
        }
        return new Matrix(m,row,col);
    }
    private static String toString(Matrix mat){
        int row = mat.getRowDimension();
        int col = mat.getColumnDimension();
        String m = "";
        for(int i = 0, k = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                m += Utils.numFormat(mat.get(i,j)) + ",";
            }
            m = m.substring(0,m.length()-1) + "\n";
        }
        return m.substring(0,m.length()-1);
    }
}
